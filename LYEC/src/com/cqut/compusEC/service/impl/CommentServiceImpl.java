package com.cqut.compusEC.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbComment;
import com.cqut.compusEC.service.CommentService;
import com.cqut.compusEC.util.DateUtil;
import com.cqut.compusEC.util.EntityIDFactory;

@Service
public class CommentServiceImpl implements CommentService{
	

	@Resource(name = "entityDao")
	EntityDao entityDao;
	
	@Override
	public Map<String, Object> getCommentList(int limit, int offset,
			String order, String sort) {
		// TODO Auto-generated method stub
		int index = limit;  // 每页数据量
		int pageNum = offset/limit + 1;  // 当前页码
		
		String[] commentProperties = new String[]{
				"a.COMMENT_ID",
				"a.COMMENT_REPLY_ID",
				"a.COMMENT_CONTENT",
				"a.COMMENT_TIME",
				"a.USER_ID",
				"a.ORDER_ID"
		};
		
		String commentTable = " tb_comment a ";
		List<Map<String, Object>> commentList = entityDao.searchWithpaging(commentProperties, commentTable, null, null, " 1=1 ", null, order, sort, index, pageNum);
		int total = entityDao.getCountByCondition("1=1", TbComment.class);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", commentList);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> addCommentInfo(String userId, String orderId,
			String commentReplyId, String commentContent) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if((userId==null || userId.equals(""))
			|| (orderId==null || orderId.equals(""))
			|| (commentContent==null || commentContent.equals(""))){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "新增订单评论，传入参数为空");
		}else{
			TbComment comment = new TbComment();
			comment.setCOMMENT_ID(EntityIDFactory.createId());
			comment.setUSER_ID(userId);
			comment.setORDER_ID(orderId);
			comment.setCOMMENT_CONTENT(commentContent);
			comment.setCOMMENT_TIME(DateUtil.chngDateString(new Date(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss));
			if(commentReplyId==null || commentReplyId.equals("")){
				comment.setCOMMENT_REPLY_ID("0");
			}else{
				comment.setCOMMENT_REPLY_ID(commentReplyId);
			}
			
			int resultInt = entityDao.save(comment);
			if(resultInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "新增订单评论成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "新增订单评论失败");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getCommentInfoByOrderId(String orderId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(orderId==null || orderId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取订单评论，传入参数为空");
		}else{
			try {
				String[] properties = new String[]{"*"};
				String table = " ( SELECT * FROM ( SELECT "
						+ " tb_comment.COMMENT_ID, "
						+ " tb_comment.COMMENT_REPLY_ID, "
						+ " tb_comment.COMMENT_CONTENT, "
						+ " tb_comment.COMMENT_TIME, "
						+ " tb_comment.USER_ID, "
						+ " tb_comment.ORDER_ID, "
						+ " tb_user.USER_NAME, "
						+ " tb_file.FILE_URL "
						+ " FROM tb_comment "
						+ " LEFT JOIN tb_user ON tb_comment.USER_ID = tb_user.USER_ID "
						+ " LEFT JOIN tb_file ON tb_user.USER_PICID = tb_file.FILE_ID "
						+ " WHERE 1=1 AND tb_comment.COMMENT_REPLY_ID=0 AND tb_comment.ORDER_ID='" + orderId + "' ORDER BY tb_comment.COMMENT_TIME DESC "
						+ ") a"
						+ " UNION ALL "
						+ " SELECT * FROM ("
						+ " SELECT "
						+ " tb_comment.COMMENT_ID, "
						+ " tb_comment.COMMENT_REPLY_ID, "
						+ " tb_comment.COMMENT_CONTENT, "
						+ " tb_comment.COMMENT_TIME, "
						+ " tb_comment.USER_ID, "
						+ " tb_comment.ORDER_ID, "
						+ " tb_user.USER_NAME, "
						+ " tb_file.FILE_URL "
						+ " FROM tb_comment "
						+ " LEFT JOIN tb_user ON tb_comment.USER_ID = tb_user.USER_ID "
						+ " LEFT JOIN tb_file ON tb_user.USER_PICID = tb_file.FILE_ID "
						+ " WHERE 1=1 AND tb_comment.COMMENT_REPLY_ID<>0 AND tb_comment.ORDER_ID='" + orderId + "' ORDER BY tb_comment.COMMENT_TIME ASC "
						+ " ) b"
						+ ") c ";
				String condition = " 1=1 ";
				
				List<Map<String, Object>> commentList = entityDao.searchForeign(properties, table, null, null, condition);
				if(commentList==null || commentList.size()<=0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "获取订单评论失败");
				}else{
					List<Map<String, Object>> resultList = new ArrayList<>();
					Map<String, Object> cachMap = new HashMap<>();
					for(int i=0; i<commentList.size(); i++){
						Map<String, Object> commentMap = commentList.get(i);
						if(commentMap.get("COMMENT_REPLY_ID").equals("0")){
							List<Map<String, Object>> childrenList = new ArrayList<>();
							commentMap.put("children", childrenList);
							cachMap.put(commentMap.get("COMMENT_ID").toString(), commentMap);
							resultList.add(commentMap);
						}else{
							Map<String, Object> parentMessage = (Map<String, Object>) cachMap.get(commentMap.get("COMMENT_REPLY_ID").toString());
							List<Map<String, Object>> childrenMessage = (List<Map<String, Object>>) parentMessage.get("children");
							childrenMessage.add(commentMap);
						}
					}
					resultMap.put("rows", resultList);
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "获取订单评论成功");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取订单评论异常");
			}
		}
		
		return resultMap;
	}
	
}
