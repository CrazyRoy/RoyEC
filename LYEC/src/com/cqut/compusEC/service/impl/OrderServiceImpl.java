package com.cqut.compusEC.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbGoodsCard;
import com.cqut.compusEC.entity.TbOrder;
import com.cqut.compusEC.service.GoodsService;
import com.cqut.compusEC.service.OrderService;
import com.cqut.compusEC.util.DateUtil;
import com.cqut.compusEC.util.EntityIDFactory;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource
	private GoodsService goodsService;
	
	@Resource(name = "entityDao")
	EntityDao entityDao;
	
	@Override
	public Map<String, Object> getOrderList(int limit, int offset,
			String order, String sort) {
		// TODO Auto-generated method stub
		int index = limit;  // 每页数据量
		int pageNum = offset/limit + 1;  // 当前页码
		
		String[] orderProperties = new String[]{
				"a.ORDER_ID",
				"a.ORDER_TIME",
				"a.ORDER_STATU",
				"a.USER_ID",
				"a.GOODS_ID",
				"a.ADDRESS_ID",
				"tb_user.USER_NAME",
				"tb_user.USER_PHONE",
				"tb_user.USER_EMAIL",
				"tb_user.SCHOOL_ID",
				"tb_goods.GOODS_TITLE",
				"tb_goods.GOODS_DES",
				"tb_goods.GOODS_ORIGINAL",
				"tb_goods.GOODS_RESALE",
				"tb_address.ADDRESS_CONTENT"
		};
		
		String orderTable = " tb_order a,tb_user,tb_goods,tb_address";
		String condition = " 1=1 AND a.USER_ID=tb_user.USER_ID AND a.GOODS_ID=tb_goods.GOODS_ID AND a.ADDRESS_ID=tb_address.ADDRESS_ID";
		List<Map<String, Object>> orderList = entityDao.searchWithpaging(orderProperties, orderTable, null, null, condition, null, order, sort, index, pageNum);
		int total = entityDao.getCountByCondition("1=1", TbOrder.class);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", orderList);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getOrderInfoByOrderId(String orderId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(orderId==null || orderId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "通过订单ID获取订单信息，传入参数为空");
		}else{
			String[] orderProperties = new String[]{
					"tb_order.ORDER_TIME",
					"tb_order.ORDER_STATU",
					"tb_order.USER_ID",
					"tb_order.GOODS_ID",
					"tb_order.ADDRESS_ID",
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
					" (SELECT FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=goods.GOODS_ID LIMIT 0,1) FILE_PATH "
			};
			String orderTable = "tb_order, tb_goods goods";
			String orderCondition = " 1=1 AND tb_order.GOODS_ID=goods.GOODS_ID AND tb_order.ORDER_ID='" + orderId + "' ";
			List<Map<String, Object>> orderList = entityDao.searchForeign(orderProperties, orderTable, null, null, orderCondition);
			
			if(orderList==null || orderList.size()<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "通过订单ID获取订单信息失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "通过订单ID获取订单信息成功");
				resultMap.put("rows", orderList);
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> insertOrderInfoByCard(String userId,
			String address) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("") || address==null || address.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据购物车中的商品信息生成订单，传入参数为空");
		}else{
			String[] goodsCardProperties = new String[]{"tb_goods_card.GOODS_ID","tb_goods_card.GOODS_CARD_ID","tb_goods.GOODS_STATUS"};
			String goodsCardTable = "tb_shoppingcard,tb_goods_card,tb_goods";
			String goodsCardCondition = " 1=1 AND tb_shoppingcard.CARD_ID=tb_goods_card.CARD_ID AND tb_goods_card.GOODS_ID=tb_goods.GOODS_ID AND tb_shoppingcard.USERID='" + userId + "' ";
			List<Map<String, Object>> goodsList = entityDao.searchForeign(goodsCardProperties, goodsCardTable, null, null, goodsCardCondition);
			
			if(goodsList==null || goodsList.size()<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据购物车中的商品信息生成订单，购物车为空");
			}else{
				String orderTable = "tb_order";
				String orderProperties = "ORDER_ID,ORDER_TIME,ORDER_STATU,USER_ID,GOODS_ID,ADDRESS_ID";
				String orderValues = "";
				String deleteCardGoodsIds = "";
				for(int i=0; i<goodsList.size(); i++){
					if(goodsList.get(i).get("GOODS_STATUS").toString().equals("1")){
						orderValues += "("
								+ "'" + EntityIDFactory.createId() + "',"
								+ "'" + DateUtil.chngDateString(new Date(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss) + "',"
								+ "'0'"
								+ "'" + userId + "',"
								+ "'" + goodsList.get(i).get("GOODS_ID").toString() + "',"
								+ "'" + address + "'),";
						deleteCardGoodsIds += "'" + goodsList.get(i).get("GOODS_CARD_ID").toString() + "',";
						
						goodsService.editGoodsInfo(goodsList.get(i).get("GOODS_ID").toString(), null, null, null, null, null, null, null, null, null, null, "3", null, null);
					}else{
					}
				}
				orderValues = orderValues.substring(0, orderValues.length()-1);
				int resultInt = entityDao.saveEntities(orderTable, orderProperties, orderValues);
				if(resultInt>0){
					deleteCardGoodsIds = deleteCardGoodsIds.substring(0, deleteCardGoodsIds.length()-1);
					String deleteCardGoodsCondition = " 1=1 AND GOODS_CARD_ID IN (" + deleteCardGoodsIds + ") ";
					resultInt = entityDao.deleteByCondition(deleteCardGoodsCondition, TbGoodsCard.class);
					if(resultInt>0){
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "根据购物车中的商品信息生成订单成功");
					}else{
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "根据购物车中的商品信息生成订单失败");
					}
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "根据购物车中的商品信息生成订单失败");
				}
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> insertOrderInfoByGoodsId(String userId, String goodsId,
			String address) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("") || goodsId==null || goodsId.equals("") || address==null || address.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据商品信息生成订单，传入参数为空");
		}else{
			TbOrder order = new TbOrder();
			order.setORDER_ID(EntityIDFactory.createId());
			order.setORDER_TIME(DateUtil.chngDateString(new Date(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss));
			order.setORDER_STATU("0");
			order.setUSER_ID(userId);
			order.setGOODS_ID(goodsId);
			order.setADDRESS_ID(address);
			
			int resultInt = entityDao.save(order);
			if(resultInt>0){
				goodsService.editGoodsInfo(goodsId, null, null, null, null, null, null, null, null, null, null, "3", null, null);
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "根据商品信息生成订单成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据商品信息生成订单失败");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteOrderInfoByOrderId(String userId,
			String orderIds) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("") || orderIds==null || orderIds.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据订单ID删除订单信息，传入参数为空");
		}else{
			String deleteCondition = " 1=1 AND USER_ID='" + userId + "' ORDER_ID IN (" + orderIds + ") ";
			int resultInt = entityDao.deleteByCondition(deleteCondition, TbOrder.class);
			if(resultInt<0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据订单ID删除订单信息失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "根据订单ID删除订单信息成功");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getOrderListByUserId(String userId, String page, String type) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取用户订单列表，传入参数为空");
		}else{
			int startIndex = 0;
			if(page==null || page.equals("")){
			}else{
				startIndex = Integer.parseInt(page)*12;
			}
			String[] properties = new String[]{
					"tb_order.ORDER_ID",
					"tb_order.ORDER_TIME",
					"tb_order.ORDER_STATU",
					"tb_order.USER_ID",
					"tb_order.GOODS_ID",
					"tb_order.ADDRESS_ID",
					"tb_goods.GOODS_TITLE",
					"tb_goods.GOODS_DES",
					"tb_goods.GOODS_RESALE",
					"tb_goods.GOODS_ORIGINAL",
					"tb_goods.GOODS_CONDITION",
					"tb_goods.GOODS_CREATEDTIME",
					"tb_goods.GOODS_RECENTACCESS",
					"tb_goods.GOODS_VIEWCONTS",
					"tb_goods.GOODS_DINGNUM",
					"tb_goods.GOODS_CAINUM",
					"tb_goods.GOODS_STATUS",
					"tb_goods.LIST_ID",
					"(SELECT tb_file.FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=tb_goods.GOODS_ID LIMIT 0,1) FILE_URL ",
					
			};
			String table = "tb_order,tb_goods";
			String condition = " 1=1 AND tb_order.GOODS_ID=tb_goods.GOODS_ID AND tb_order.USER_ID='" + userId + "' ";
			if(type==null || type.equals("")){
			}else{
				condition += " AND tb_order.ORDER_STATU='" + type + "' ";
			}
			condition += " LIMIT " + startIndex + ",12";
			List<Map<String, Object>> resultList = entityDao.searchForeign(properties, table, null, null, condition);
			
			if(resultList==null || resultList.size()<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取用户订单列表失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取用户订单列表成功");
				resultMap.put("rows", resultList);
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> updateOrderStatu(String orderId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(orderId==null || orderId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "修改订单状态，传入参数为空");
		}else{
			TbOrder order = new TbOrder();
			order.setORDER_STATU("1");
			int resultInt = entityDao.updatePropByID(order, orderId);
			if(resultInt>0){
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "修改订单状态成功");
			}else{
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "修改订单状态失败");
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getOrderListTotalByUserId(String userId,
			String type) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取用户订单列表，传入参数为空");
		}else{
			String[] properties = new String[]{"*"};
			String table = "tb_order,tb_goods";
			String condition = " 1=1 AND tb_order.GOODS_ID=tb_goods.GOODS_ID AND tb_order.USER_ID='" + userId + "' ";
			if(type==null || type.equals("")){
			}else{
				condition += " AND tb_order.ORDER_STATU='" + type + "' ";
			}
			int result = entityDao.searchForeign(properties, table, null, null, condition).size();
			
			if(result<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取用户订单列表总数失败");
				resultMap.put("pageNum", 0);
			}else{
				int pageNum = result/6+1;
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取用户订单列表总数成功");
				resultMap.put("pageNum", pageNum);
			}
		}
		return resultMap;
	}
}
