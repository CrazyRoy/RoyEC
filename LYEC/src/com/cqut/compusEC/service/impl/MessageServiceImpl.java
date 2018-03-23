package com.cqut.compusEC.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbMessage;
import com.cqut.compusEC.service.MessageService;
import com.cqut.compusEC.util.DateUtil;
import com.cqut.compusEC.util.EntityIDFactory;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Resource(name = "entityDao")
	EntityDao entityDao;
	
	@Override
	public Map<String, Object> getMessageList(int limit, int offset,
			String order, String sort) {
		// TODO Auto-generated method stub
		int index = limit;  // 每页数据量
		int pageNum = offset/limit + 1;  // 当前页码
		
		String[] messageProperties = new String[]{
				"a.MESSAGE_ID",
				"a.MESSAGE_PARENTID MESSAGE_REPLY_ID",
				"a.MESSAGE_CONTENT",
				"a.MESSAGE_TIME",
				"a.GOODS_ID",
				"a.BUYERSID",
				"a.SELLERID"
		};
		
		String messageTable = " tb_message a ";
		List<Map<String, Object>> messageList = entityDao.searchWithpaging(messageProperties, messageTable, null, null, " 1=1 ", null, order, sort, index, pageNum);
		int total = entityDao.getCountByCondition("1=1", TbMessage.class);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", messageList);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getMessageListByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(goodsId==null || goodsId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据商品获取商品留言，传入参数为空");
		}else{
			try {
				String[] messageProperties = new String[]{"*"};
				
				String messageTable = " ( SELECT * FROM (SELECT *,"
						+ "(SELECT tb_user.USER_NAME FROM tb_user WHERE tb_message.BUYERSID=tb_user.USER_ID) BUYERSNAME, "
						+ "(SELECT tb_file.FILE_URL FROM tb_file,tb_user WHERE tb_message.BUYERSID=tb_user.USER_ID AND tb_user.USER_PICID=tb_file.FILE_ID) BUYERSPIC, "
						+ "(SELECT tb_user.USER_NAME FROM tb_user WHERE tb_message.SELLERID=tb_user.USER_ID) SELLERNAME, "
						+ "(SELECT tb_file.FILE_URL FROM tb_file,tb_user WHERE tb_message.SELLERID=tb_user.USER_ID AND tb_user.USER_PICID=tb_file.FILE_ID) SELLERPIC "
						+ " FROM tb_message WHERE tb_message.MESSAGE_PARENTID=0 AND tb_message.GOODS_ID='" + goodsId + "' ORDER BY tb_message.MESSAGE_TIME DESC) b"
						+ " UNION ALL "
						+ " SELECT *,(SELECT tb_user.USER_NAME FROM tb_user WHERE tb_message.BUYERSID=tb_user.USER_ID) BUYERSNAME, "
						+ "(SELECT tb_file.FILE_URL FROM tb_file,tb_user WHERE tb_message.BUYERSID=tb_user.USER_ID AND tb_user.USER_PICID=tb_file.FILE_ID) BUYERSPIC, "
						+ "(SELECT tb_user.USER_NAME FROM tb_user WHERE tb_message.SELLERID=tb_user.USER_ID) SELLERNAME, "
						+ "(SELECT tb_file.FILE_URL FROM tb_file,tb_user WHERE tb_message.SELLERID=tb_user.USER_ID AND tb_user.USER_PICID=tb_file.FILE_ID) SELLERPIC "
						+ " FROM tb_message WHERE tb_message.MESSAGE_PARENTID<>0 AND tb_message.GOODS_ID='" + goodsId + "') a ";
				String messageCondition = " 1=1 ";
				List<Map<String, Object>> messageList = entityDao.searchForeign(messageProperties, messageTable, null, null, messageCondition);
				
				if(messageList==null || messageList.size()<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "根据商品获取商品留言失败");
				}else{
					List<Map<String, Object>> resultList = new ArrayList<>();
					Map<String, Object> cachMap = new HashMap<>();
					for(int i=0; i<messageList.size(); i++){
						Map<String, Object> messageMap = messageList.get(i);
						if(messageMap.get("MESSAGE_PARENTID").equals("0")){
							List<Map<String, Object>> childrenList = new ArrayList<>();
							messageMap.put("children", childrenList);
							cachMap.put(messageMap.get("MESSAGE_ID").toString(), messageMap);
							resultList.add(messageMap);
						}else{
							@SuppressWarnings("unchecked")
							Map<String, Object> parentMessage = (Map<String, Object>) cachMap.get(messageMap.get("MESSAGE_PARENTID").toString());
							@SuppressWarnings("unchecked")
							List<Map<String, Object>> childrenMessage = (List<Map<String, Object>>) parentMessage.get("children");
							childrenMessage.add(messageMap);
						}
					}
					resultMap.put("rows", resultList);
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "根据商品获取商品留言成功");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据商品获取商品留言异常");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> addMessageInfo(String goodsId,
			String messageParentId, String messageContent, String messageTime,
			String buyersId, String sellerId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if((goodsId==null || goodsId.equals(""))
			|| (messageContent==null || messageContent.equals(""))
			|| (buyersId==null || buyersId.equals(""))
			|| (sellerId==null || sellerId.equals(""))){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "新增商品留言，传入参数为空");
		}else{
			TbMessage message = new TbMessage();
			message.setMESSAGE_ID(EntityIDFactory.createId());
			message.setGOODS_ID(goodsId);
			if(messageParentId==null || messageParentId.equals("")){
				message.setMESSAGE_PARENTID("0");
			}else{
				message.setMESSAGE_PARENTID(messageParentId);
			}
			message.setMESSAGE_CONTENT(messageContent);
			message.setMESSAGE_TIME(DateUtil.chngDateString(new Date(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss));
			message.setBUYERSID(buyersId);
			message.setSELLERID(sellerId);
			
			int resultInt = entityDao.save(message);
			if(resultInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "新增商品留言成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "新增商品留言失败");
			}
		}
		return resultMap;
	}
	
}
