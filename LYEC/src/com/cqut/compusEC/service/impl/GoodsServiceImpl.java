package com.cqut.compusEC.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbFile;
import com.cqut.compusEC.entity.TbGoods;
import com.cqut.compusEC.entity.TbGoodsUser;
import com.cqut.compusEC.service.GoodsService;
import com.cqut.compusEC.util.DateUtil;
import com.cqut.compusEC.util.EntityIDFactory;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Resource(name = "entityDao")
	EntityDao entityDao;
	
	@Override
	public Map<String, Object> insertGoodsInfo(String userId, String goodsTitle, 
			String goodsDes, String goodsOrignal, String goodsResale, String goodsCondition,
			String listId, String fileNames, String filePaths) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if((goodsTitle==null || goodsTitle.equals(""))
			|| (userId==null || userId.equals("") || userId.equals("null"))
			|| (goodsDes==null || goodsDes.equals(""))
			|| (goodsOrignal==null || goodsOrignal.equals(""))
			|| (goodsCondition==null || goodsCondition.equals(""))
			|| (goodsResale==null || goodsResale.equals(""))
			|| (listId==null || listId.equals(""))
			|| (fileNames==null || fileNames.equals(""))
			|| (filePaths==null || filePaths.equals(""))){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "发布商品，传入信息为空");
		}else{
			TbGoods goods = new TbGoods();
			goods.setGOODS_ID(EntityIDFactory.createId());
			goods.setGOODS_TITLE(goodsTitle);
			goods.setGOODS_DES(goodsDes);
			goods.setGOODS_ORIGINAL(goodsOrignal);
			goods.setGOODS_RESALE(goodsResale);
			goods.setGOODS_CONDITION(goodsCondition);
			goods.setLIST_ID(listId);
			goods.setGOODS_CREATEDTIME(DateUtil.chngDateString(new Date(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss));
			goods.setGOODS_RECENTACCESS(DateUtil.chngDateString(new Date(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss));
			goods.setGOODS_VIEWCONTS(0);
			goods.setGOODS_DINGNUM("0");
			goods.setGOODS_CAINUM("0");
			goods.setGOODS_STATUS("0");
			
			int resultInt = entityDao.save(goods);
			
			if(resultInt>0){
				TbGoodsUser goodsUser = new TbGoodsUser();
				goodsUser.setGOODS_USER_ID(EntityIDFactory.createId());
				goodsUser.setGOODS_ID(goods.getGOODS_ID());
				goodsUser.setUSER_ID(userId);
				resultInt = entityDao.save(goodsUser);
				
				if(resultInt>0){
					String[] fileNameArray = fileNames.split(",");
					String[] filePathArray = filePaths.split(",");
					String fileTable = " tb_file ";
					String fileProperties = " FILE_ID,FILE_URL,FILE_NAME,FILE_TYPE,GOODS_ID ";
					String fileVaules = "";
					for(int i=0; i<fileNameArray.length; i++){
						TbFile file = new TbFile();
						file.setFILE_ID(EntityIDFactory.createId());
						file.setFILE_NAME(fileNameArray[i]);
						file.setFILE_URL(filePathArray[i]);
						file.setFILE_TYPE("0");
						file.setGOODS_ID(goods.getGOODS_ID());
						
						fileVaules += " ('"
								+ file.getFILE_ID() + "','"
								+ file.getFILE_URL() + "','"
								+ file.getFILE_NAME() + "','"
								+ file.getFILE_TYPE() + "','"
								+ file.getGOODS_ID() + "'),";
					}
					fileVaules = fileVaules.substring(0, fileVaules.length()-1);
					
					resultInt = entityDao.saveEntities(fileTable, fileProperties, fileVaules);
					if(resultInt>0){
						resultMap.put("resultCode", "0000");
						resultMap.put("resultMessage", "发布商品成功");
						resultMap.put("goodsId", goods.getGOODS_ID());
					}else{
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "发布商品失败");
					}
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "发布商品失败");
				}
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "发布商品失败");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsList(int limit, int offset,
			String order, String sort) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int index = limit;  // 每页数据量
			int pageNum = offset/limit + 1;  // 当前页码
			
			String[] goodsProperties = new String[]{
					"goods.GOODS_ID",
					"goods.GOODS_TITLE",
					"goods.GOODS_DES",
					"goods.GOODS_ORIGINAL",
					"goods.GOODS_RESALE",
					"goods.GOODS_CONDITION",
					"goods.GOODS_CREATEDTIME",
					"goods.GOODS_RECENTACCESS",
					"goods.GOODS_VIEWCONTS",
					"goods.GOODS_DINGNUM",
					"goods.GOODS_CAINUM",
					"goods.GOODS_STATUS",
					"goods.LIST_ID",
					"tb_list.LIST_NAME",
			};
			String goodsTable = "tb_goods goods, tb_list";
			String condition = " 1=1 AND goods.LIST_ID=tb_list.LIST_ID ";
			List<Map<String, Object>> goodsList = entityDao.searchWithpaging(goodsProperties, goodsTable, null, null, condition, null, order, sort, index, pageNum);
			int total = entityDao.getCountByCondition(" 1=1 ", TbGoods.class);

			if(goodsList==null || goodsList.size()<0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取商品信息失败");
				resultMap.put("total", 0);
				resultMap.put("rows", new ArrayList<>());
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取商品信息成功");
				resultMap.put("total", total);
				resultMap.put("rows", goodsList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取商品信息异常");
			resultMap.put("total", 0);
			resultMap.put("rows", new ArrayList<>());
		}
		
		
		return resultMap;
	}

	@Override
	public Map<String, Object> editGoodsInfo(String goodsId, String goodsTitle,
			String goodsDes, String goodsOrignal, String goodsResale, String goodsCondition,
			String listId, String goodsRecentAccess, String goodsViewConts,
			String goodsDingNum, String goodsCaiNum, String goodsStatus,
			String fileNames, String filePaths) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(goodsId==null || goodsId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改商品信息，传入参数为空");
		}else{
			TbGoods tbGoods = new TbGoods();
			if(goodsTitle==null || goodsTitle.equals("")){
				tbGoods.setGOODS_TITLE(null);
			}else{
				tbGoods.setGOODS_TITLE(goodsTitle);
			}
			if(goodsDes==null || goodsDes.equals("")){
				tbGoods.setGOODS_DES(null);
			}else{
				tbGoods.setGOODS_DES(goodsDes);
			}
			if(goodsOrignal==null || goodsOrignal.equals("")){
				tbGoods.setGOODS_ORIGINAL(null);
			}else{
				tbGoods.setGOODS_ORIGINAL(goodsOrignal);
			}
			if(goodsResale==null || goodsResale.equals("")){
				tbGoods.setGOODS_RESALE(null);
			}else{
				tbGoods.setGOODS_RESALE(goodsResale);
			}
			if(goodsCondition==null || goodsCondition.equals("")){
				tbGoods.setGOODS_CONDITION(null);
			}else{
				tbGoods.setGOODS_CONDITION(goodsCondition);
			}
			if(listId==null || listId.equals("")){
				tbGoods.setLIST_ID(null);
			}else{
				tbGoods.setLIST_ID(listId);
			}
//			if(goodsRecentAccess==null || goodsRecentAccess.equals("")){
//				tbGoods.setGOODS_RECENTACCESS(null);
//			}else{
//				tbGoods.setGOODS_RECENTACCESS(goodsRecentAccess);
//			}
			if(goodsViewConts == null || goodsViewConts == ""){
				tbGoods.setGOODS_VIEWCONTS(0);
			}else{
				tbGoods.setGOODS_VIEWCONTS(Integer.parseInt(goodsViewConts));
				tbGoods.setGOODS_RECENTACCESS(DateUtil.chngDateString(new Date(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss));
			}
			if(goodsDingNum==null || goodsDingNum.equals("")){
				tbGoods.setGOODS_DINGNUM(null);
			}else{
				tbGoods.setGOODS_DINGNUM(goodsDingNum);
			}
			if(goodsCaiNum==null || goodsCaiNum.equals("")){
				tbGoods.setGOODS_CAINUM(null);
			}else{
				tbGoods.setGOODS_CAINUM(goodsCaiNum);
			}
			if(goodsStatus==null || goodsStatus.equals("")){
				tbGoods.setGOODS_STATUS(null);
			}else{
				tbGoods.setGOODS_STATUS(goodsStatus);
			}
			int resultInt = entityDao.updatePropByID(tbGoods, goodsId);
			if(resultInt>0){
				if(fileNames==null || fileNames.equals("") || filePaths==null || filePaths.equals("")){
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "修改商品信息成功");
				}else{
					String condition = " 1=1 AND GOODS_ID='" + goodsId + "' ";
					resultInt = entityDao.deleteByCondition(condition, TbFile.class);
					
					String[] fileNameArray = fileNames.split(",");
					String[] filePathArray = filePaths.split(",");
					String fileTable = " tb_file ";
					String fileProperties = " FILE_ID,FILE_URL,FILE_NAME,FILE_TYPE,GOODS_ID ";
					String fileVaules = "";
					for(int i=0; i<fileNameArray.length; i++){
						TbFile file = new TbFile();
						file.setFILE_ID(EntityIDFactory.createId());
						file.setFILE_NAME(fileNameArray[i]);
						file.setFILE_URL(filePathArray[i]);
						file.setFILE_TYPE("0");
						file.setGOODS_ID(goodsId);
						
						fileVaules += " ('"
								+ file.getFILE_ID() + "','"
								+ file.getFILE_URL() + "','"
								+ file.getFILE_NAME() + "','"
								+ file.getFILE_TYPE() + "','"
								+ file.getGOODS_ID() + "'),";
					}
					fileVaules = fileVaules.substring(0, fileVaules.length()-1);
					
					resultInt = entityDao.saveEntities(fileTable, fileProperties, fileVaules);
					if(resultInt>0){
						resultMap.put("resultCode", "0000");
						resultMap.put("resultMessage", "修改商品信息成功");
					}else{
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "修改商品信息失败");
					}
				}
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改商品信息失败");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteGoods(String goodsIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(goodsIds==null || goodsIds.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "删除商品信息，传入参数为空");
		}else{
			String delCondition = " 1=1 AND GOODS_ID IN (" + goodsIds + ") ";
			int result = entityDao.deleteByCondition(delCondition, TbGoods.class); //调用DAO的方法删除多个帐号信息
			
			if(result<0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "删除商品信息失败");
			}else{
				result = entityDao.deleteByCondition(delCondition, TbFile.class);
				
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "删除商品信息成功");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsListByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(goodsId==null || goodsId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "通过商品ID获取商品信息，传入参数为空");
		}else{
			try {
				String[] goodsProperties = new String[]{
						"goods.GOODS_ID",
						"goods.GOODS_TITLE",
						"goods.GOODS_DES",
						"goods.GOODS_ORIGINAL",
						"goods.GOODS_RESALE",
						"goods.GOODS_CONDITION",
						"goods.GOODS_CREATEDTIME",
						"goods.GOODS_RECENTACCESS",
						"goods.GOODS_VIEWCONTS",
						"goods.GOODS_DINGNUM",
						"goods.GOODS_CAINUM",
						"goods.GOODS_STATUS",
						"goods.LIST_ID",
						"tb_list.LIST_NAME",
				};
				String goodsTable = "tb_goods goods, tb_list";
				String goodsCondition = " 1=1 AND goods.LIST_ID=tb_list.LIST_ID AND goods.GOODS_ID='" + goodsId + "' ";
				List<Map<String, Object>> goodsList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
				
				if(goodsList==null || goodsList.size()<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "通过商品ID获取商品信息失败");
				}else{
					String[] fileProperties = new String[]{
							"FILE_ID",
							"FILE_URL",
							"FILE_NAME",
							"FILE_DES",
							"FILE_TYPE",
							"GOODS_ID"
					};
					String fileTable = "tb_file";
					String fileCondition = " 1=1 AND FILE_TYPE=0 AND GOODS_ID='" + goodsId + "' ";
					List<Map<String, Object>> fileList = entityDao.searchForeign(fileProperties, fileTable, null, null, fileCondition);
					
					if(fileList==null || fileList.size()<0){
						goodsList.get(0).put("fileChildren", new ArrayList<>());
					}else{
						goodsList.get(0).put("fileChildren", fileList);
					}
					
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "通过商品ID获取商品信息成功");
					resultMap.put("rows", goodsList);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "通过商品ID获取商品信息异常");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsListByCreatetime(String index) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(index==null || index.equals("")){
			index = "0";
		}
		try {
			int startIndex = Integer.parseInt(index)*5;
			String[] goodsProperties = new String[]{
					"goods.GOODS_ID",
					"goods.GOODS_TITLE",
					"goods.GOODS_DES",
					"goods.GOODS_ORIGINAL",
					"goods.GOODS_RESALE",
					"goods.GOODS_CONDITION",
					"goods.GOODS_CREATEDTIME",
					"goods.GOODS_RECENTACCESS",
					"goods.GOODS_VIEWCONTS",
					"goods.GOODS_DINGNUM",
					"goods.GOODS_CAINUM",
					"goods.GOODS_STATUS",
					"goods.LIST_ID",
					"tb_list.LIST_NAME",
					" (SELECT FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=goods.GOODS_ID LIMIT 0,1) FILE_PATH "
			};
			String goodsTable = "tb_goods goods, tb_list";
			String goodsCondition = " 1=1 AND goods.LIST_ID=tb_list.LIST_ID AND goods.GOODS_STATUS=1 ORDER BY goods.GOODS_CREATEDTIME DESC LIMIT " + startIndex + ",5 ";
			List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
			
			if(resultList==null || resultList.size()<0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "通过获取最新商品信息失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "通过获取最新商品信息成功");
				resultMap.put("rows", resultList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "通过获取最新商品信息异常");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsListBySchoole(String userId, String index) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(index==null || index.equals("")
			|| userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "通过获取同校商品信息，传入参数为空");
		}else{
			try {
				int startIndex = Integer.parseInt(index)*5;
				String[] goodsProperties = new String[]{
						"goods.GOODS_ID",
						"goods.GOODS_TITLE",
						"goods.GOODS_DES",
						"goods.GOODS_ORIGINAL",
						"goods.GOODS_RESALE",
						"goods.GOODS_CONDITION",
						"goods.GOODS_CREATEDTIME",
						"goods.GOODS_RECENTACCESS",
						"goods.GOODS_VIEWCONTS",
						"goods.GOODS_DINGNUM",
						"goods.GOODS_CAINUM",
						"goods.GOODS_STATUS",
						"goods.LIST_ID",
						"tb_list.LIST_NAME",
						" (SELECT FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=goods.GOODS_ID LIMIT 0,1) FILE_PATH "
				};
				String goodsTable = "tb_user,tb_goods_user,tb_goods goods, tb_list";
				String goodsCondition = " 1=1 AND tb_user.USER_ID=tb_goods_user.USER_ID AND tb_goods_user.GOODS_ID=goods.GOODS_ID AND goods.LIST_ID=tb_list.LIST_ID AND goods.GOODS_STATUS=1 AND tb_user.SCHOOL_ID IN (SELECT SCHOOL_ID FROM tb_user WHERE USER_ID='" + userId + "') LIMIT " + startIndex + ",5 ";
				List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
				
				if(resultList==null || resultList.size()<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "通过获取同校商品信息失败");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "通过获取同校商品信息成功");
					resultMap.put("rows", resultList);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "通过获取同校商品信息异常");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsListByGuess() {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		try {
			List<TbGoods> listGoods = entityDao.getByCondition(" 1=1 ", TbGoods.class);
			String guessCondition = guessGoodsCondition(listGoods);
			
			String[] goodsProperties = new String[]{
					"goods.GOODS_ID",
					"goods.GOODS_TITLE",
					"goods.GOODS_DES",
					"goods.GOODS_ORIGINAL",
					"goods.GOODS_RESALE",
					"goods.GOODS_CONDITION",
					"goods.GOODS_CREATEDTIME",
					"goods.GOODS_RECENTACCESS",
					"goods.GOODS_VIEWCONTS",
					"goods.GOODS_DINGNUM",
					"goods.GOODS_CAINUM",
					"goods.GOODS_STATUS",
					"goods.LIST_ID",
					"tb_list.LIST_NAME",
					" (SELECT FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=goods.GOODS_ID LIMIT 0,1) FILE_PATH "
			};
			String goodsTable = "tb_goods goods, tb_list";
			String goodsCondition = " 1=1 AND goods.LIST_ID=tb_list.LIST_ID AND goods.GOODS_STATUS=1 AND goods.GOODS_ID IN (" + guessCondition + ") ";
			List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
			
			if(resultList==null || resultList.size()<0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "通过获取随机商品信息失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "通过获取随机商品信息成功");
				resultMap.put("rows", resultList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "通过获取随机商品信息异常");
		}
		
		return resultMap;
	}
	
	private String guessGoodsCondition(List<TbGoods> goodsList){
		Map<String, Object> cachMap = new HashMap<>();
		int total = goodsList.size();
		String result = "";
		for(int i=0; i<5;){
			if(i>=goodsList.size()){
				break;
			}
			int index = (int) (Math.random()*total);
			if(cachMap.containsKey(""+index)){
				continue;
			}
			result += "'" + goodsList.get(index).getGOODS_ID() + "',";
			cachMap.put(""+index, index);
			i++;
		}
		result = result.substring(0, result.length()-1);
		
		return result;
	}

	@Override
	public Map<String, Object> getGoodsListByListType(String typeId, String page, String number) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(typeId==null || typeId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "通过获取指定类型的商品信息，传入参数为空");
		}else{
			try {
				int startIndex = 0;
				int pageNumber = 6;
				if(number==null || number.equals("")){
				}else{
					pageNumber = Integer.parseInt(number);
				}
				if(page==null || page.equals("")){
				}else{
					startIndex = Integer.parseInt(page)*pageNumber;
				}
				String[] goodsProperties = new String[]{
						"goods.GOODS_ID",
						"goods.GOODS_TITLE",
						"goods.GOODS_DES",
						"goods.GOODS_ORIGINAL",
						"goods.GOODS_RESALE",
						"goods.GOODS_CONDITION",
						"goods.GOODS_CREATEDTIME",
						"goods.GOODS_RECENTACCESS",
						"goods.GOODS_VIEWCONTS",
						"goods.GOODS_DINGNUM",
						"goods.GOODS_CAINUM",
						"goods.GOODS_STATUS",
						"goods.LIST_ID",
						"tb_list.LIST_NAME",
						" (SELECT FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=goods.GOODS_ID LIMIT 0,1) FILE_PATH "
				};
				String goodsTable = " tb_goods goods LEFT JOIN tb_list ON goods.LIST_ID = tb_list.LIST_ID LEFT JOIN tb_type ON tb_list.TYPE_ID = tb_type.TYPE_ID ";
				String goodsCondition = " 1=1 AND goods.GOODS_STATUS=1 AND tb_list.TYPE_ID='" + typeId + "' ORDER BY goods.GOODS_CREATEDTIME DESC LIMIT  " + startIndex + "," + pageNumber;
				List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
				
				if(resultList==null || resultList.size()<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "通过获取指定类型的商品信息失败");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "通过获取指定类型的商品信息成功");
					resultMap.put("rows", resultList);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "通过获取指定类型的商品信息异常");
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsListByListViewConts() {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		try {
			String[] goodsProperties = new String[]{
					"goods.GOODS_ID",
					"goods.GOODS_TITLE",
					"goods.GOODS_DES",
					"goods.GOODS_ORIGINAL",
					"goods.GOODS_RESALE",
					"goods.GOODS_CONDITION",
					"goods.GOODS_CREATEDTIME",
					"goods.GOODS_RECENTACCESS",
					"goods.GOODS_VIEWCONTS",
					"goods.GOODS_DINGNUM",
					"goods.GOODS_CAINUM",
					"goods.GOODS_STATUS",
					"goods.LIST_ID",
					"tb_list.LIST_NAME",
					" (SELECT FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=goods.GOODS_ID LIMIT 0,1) FILE_PATH "
			};
			String goodsTable = "tb_goods goods, tb_list";
			String goodsCondition = " 1=1 AND goods.GOODS_STATUS=1 AND goods.LIST_ID=tb_list.LIST_ID ORDER BY goods.GOODS_VIEWCONTS DESC,goods.GOODS_CREATEDTIME DESC LIMIT 0,6 ";
			List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
			
			if(resultList==null || resultList.size()<0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "通过获取热门商品信息失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "通过获取热门商品信息成功");
				resultMap.put("rows", resultList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "通过获取热门商品信息异常");
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsListByListId(String listId, String page) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(listId==null || listId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据目录获取商品信息，传入参数为空");
		}else{
			try {
				int startIndex = 0;
				if(page==null || page.equals("")){
				}else{
					startIndex = Integer.parseInt(page)*12;
				}
				String[] goodsProperties = new String[]{
						"goods.GOODS_ID",
						"goods.GOODS_TITLE",
						"goods.GOODS_DES",
						"goods.GOODS_ORIGINAL",
						"goods.GOODS_RESALE",
						"goods.GOODS_CONDITION",
						"goods.GOODS_CREATEDTIME",
						"goods.GOODS_RECENTACCESS",
						"goods.GOODS_VIEWCONTS",
						"goods.GOODS_DINGNUM",
						"goods.GOODS_CAINUM",
						"goods.GOODS_STATUS",
						"goods.LIST_ID",
						"tb_list.LIST_NAME",
						" (SELECT FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=goods.GOODS_ID LIMIT 0,1) FILE_PATH "
				};
				String goodsTable = "tb_goods goods, tb_list";
				String goodsCondition = " 1=1 AND goods.GOODS_STATUS=1 AND goods.LIST_ID=tb_list.LIST_ID AND goods.LIST_ID='" + listId + "' ORDER BY goods.GOODS_CREATEDTIME DESC LIMIT " + startIndex + ",12 ";
				List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
				
				if(resultList==null || resultList.size()<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "根据目录获取商品信息失败");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "根据目录获取商品信息成功");
					resultMap.put("rows", resultList);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据目录获取商品信息异常");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> insertGoodsByGoodsId(String goodsId, String userId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(goodsId==null || goodsId.equals("") || userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "转卖商品，传入参数为空");
		}else{
			TbGoods goods = entityDao.getByID(goodsId, TbGoods.class);
			goods.setGOODS_ID(EntityIDFactory.createId());
			
			int resultInt = entityDao.save(goods);
			if(resultInt>0){
				TbGoodsUser goodsUser = new TbGoodsUser();
				goodsUser.setGOODS_USER_ID(EntityIDFactory.createId());
				goodsUser.setGOODS_ID(goods.getGOODS_ID());
				goodsUser.setUSER_ID(userId);
				
				resultInt = entityDao.save(goodsUser);
				if(resultInt>0){
					String fileCondition = " 1=1 AND GOODS_ID='" + goodsId + "' ";
					List<TbFile> fileList = entityDao.getByCondition(fileCondition, TbFile.class);
					String fileTable = " tb_file ";
					String fileProperties = " FILE_ID,FILE_URL,FILE_NAME,FILE_TYPE,GOODS_ID ";
					String fileVaules = "";
					for(int i=0; i<fileList.size(); i++){
						TbFile file = fileList.get(i);
						file.setFILE_ID(EntityIDFactory.createId());
						file.setGOODS_ID(goods.getGOODS_ID());
						
						fileVaules += " ('"
								+ file.getFILE_ID() + "','"
								+ file.getFILE_URL() + "','"
								+ file.getFILE_NAME() + "','"
								+ file.getFILE_TYPE() + "','"
								+ file.getGOODS_ID() + "'),";
					}
					fileVaules = fileVaules.substring(0, fileVaules.length()-1);
					
					resultInt = entityDao.saveEntities(fileTable, fileProperties, fileVaules);
					if(resultInt>0){
						resultMap.put("resultCode", "0000");
						resultMap.put("resultMessage", "转卖商品成功");
					}else{
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "转卖商品失败");
					}
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "转卖商品失败");
				}
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "转卖商品失败");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsListByUserId(String userId, String page, String type) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据用户获取商品信息，传入参数为空");
		}else{
			try {
				int startIndex = 0;
				if(page==null || page.equals("")){
				}else{
					startIndex = Integer.parseInt(page)*6;
				}
				String[] goodsProperties = new String[]{
						"goods.GOODS_ID",
						"goods.GOODS_TITLE",
						"goods.GOODS_DES",
						"goods.GOODS_ORIGINAL",
						"goods.GOODS_RESALE",
						"goods.GOODS_CONDITION",
						"goods.GOODS_CREATEDTIME",
						"goods.GOODS_RECENTACCESS",
						"goods.GOODS_VIEWCONTS",
						"goods.GOODS_DINGNUM",
						"goods.GOODS_CAINUM",
						"goods.GOODS_STATUS",
						"goods.LIST_ID",
						"tb_list.LIST_NAME",
						" (SELECT FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=goods.GOODS_ID LIMIT 0,1) FILE_PATH "
				};
				String goodsTable = "tb_goods goods, tb_list, tb_goods_user";
				String goodsCondition = " 1=1 AND goods.LIST_ID=tb_list.LIST_ID AND goods.GOODS_ID=tb_goods_user.GOODS_ID AND tb_goods_user.USER_ID='" + userId + "' ";
				if(type==null || type.equals("")){
				}else{
					goodsCondition += " AND goods.GOODS_STATUS='" + type + "' ";
				}
				goodsCondition += " ORDER BY goods.GOODS_CREATEDTIME DESC LIMIT " + startIndex + ",6 ";
				List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
				
				if(resultList==null || resultList.size()<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "根据用户获取商品信息失败");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "根据用户获取商品信息成功");
					resultMap.put("rows", resultList);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据用户获取商品信息异常");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsTotalByUserId(String userId, String type) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据用户获取商品信息总数，传入参数为空");
		}else{
			try {
				String table = "tb_goods,tb_goods_user";
				String condition = " 1=1 AND tb_goods.GOODS_ID=tb_goods_user.GOODS_ID AND USER_ID='" + userId + "' ";
				if(type==null || type.equals("")){
				}else{
					condition += " AND tb_goods.GOODS_STATUS='" +type + "' ";				
				}
				int total = entityDao.searchForeign(new String[]{"*"}, table, null, null, condition).size();
				int pageNum = (total + 6 - 1)/6;
				
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "根据用户获取商品信息总数成功");
				resultMap.put("pageNum", pageNum);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据用户获取商品信息总数异常");
				resultMap.put("pageNum", 0);
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsTotalByListId(String listId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(listId==null || listId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据目录获取商品信息总数，传入参数为空");
		}else{
			try {
				String[] goodsProperties = new String[]{"*"};
				String goodsTable = "tb_goods goods, tb_list";
				String goodsCondition = " 1=1 AND goods.GOODS_STATUS=1 AND goods.LIST_ID=tb_list.LIST_ID AND goods.LIST_ID='" + listId + "' ";
				List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
				
				if(resultList==null || resultList.size()<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "根据目录获取商品信息总数失败");
					resultMap.put("pageNum", 0);
				}else{
					int pageNum = (resultList.size() + 12 - 1)/12;
					
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "根据目录获取商品信息总数成功");
					resultMap.put("pageNum", pageNum);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据目录获取商品信息总数异常");
				resultMap.put("pageNum", 0);
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsTotalByListType(String typeId, String number) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(typeId==null || typeId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "通过获取指定类型的商品信息，传入参数为空");
		}else{
			try {
				int pageNumber = 12;
				if(number==null || number.equals("")){
				}else{
					pageNumber = Integer.parseInt(number);
				}
				
				String[] goodsProperties = new String[]{"*"};
				String goodsTable = " tb_goods goods LEFT JOIN tb_list ON goods.LIST_ID = tb_list.LIST_ID LEFT JOIN tb_type ON tb_list.TYPE_ID = tb_type.TYPE_ID ";
				String goodsCondition = " 1=1 AND goods.GOODS_STATUS=1 AND tb_list.TYPE_ID='" + typeId + "' ";
				List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
				
				if(resultList==null || resultList.size()<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "通过获取指定类型的商品信息失败");
					resultMap.put("pageNum", 0);
				}else{
					int pageNum = (resultList.size() + pageNumber - 1)/pageNumber;
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "通过获取指定类型的商品信息成功");
					resultMap.put("pageNum", pageNum);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "通过获取指定类型的商品信息异常");
				resultMap.put("pageNum", 0);
			}
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsListBySearch(String seachContent, String page, String number) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(seachContent==null || seachContent.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据用户获取商品信息，传入参数为空");
		}else{
			try {
				int startIndex = 0;
				int pageNumber = 12;
				if(number==null || number.equals("")){
				}else{
					pageNumber = Integer.parseInt(number);
				}
				if(page==null || page.equals("")){
				}else{
					startIndex = Integer.parseInt(page)*pageNumber;
				}
				String[] goodsProperties = new String[]{
						"goods.GOODS_ID",
						"goods.GOODS_TITLE",
						"goods.GOODS_DES",
						"goods.GOODS_ORIGINAL",
						"goods.GOODS_RESALE",
						"goods.GOODS_CONDITION",
						"goods.GOODS_CREATEDTIME",
						"goods.GOODS_RECENTACCESS",
						"goods.GOODS_VIEWCONTS",
						"goods.GOODS_DINGNUM",
						"goods.GOODS_CAINUM",
						"goods.GOODS_STATUS",
						"goods.LIST_ID",
						"tb_list.LIST_NAME",
						" (SELECT FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=goods.GOODS_ID LIMIT 0,1) FILE_PATH "
				};
				String goodsTable = " tb_goods goods, tb_list ";
				String goodsCondition = " 1=1 AND goods.LIST_ID=tb_list.LIST_ID AND goods.GOODS_TITLE LIKE '%" + seachContent + "%' ";
				goodsCondition += " ORDER BY goods.GOODS_CREATEDTIME DESC LIMIT " + startIndex + "," + pageNumber;
				List<Map<String, Object>> resultList = entityDao.searchForeign(goodsProperties, goodsTable, null, null, goodsCondition);
				
				if(resultList==null || resultList.size()<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "搜索商品信息失败");
				}else{
					resultMap.put("resultCode", "0000");
					resultMap.put("resultMessage", "搜索商品信息成功");
					resultMap.put("rows", resultList);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "搜索商品信息异常");
			}
		}
		return resultMap;
	}
}
