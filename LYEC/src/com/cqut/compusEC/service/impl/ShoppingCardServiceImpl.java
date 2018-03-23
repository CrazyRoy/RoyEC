package com.cqut.compusEC.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.dao.EntityDao;
import com.cqut.compusEC.entity.TbGoodsCard;
import com.cqut.compusEC.entity.TbShoppingCard;
import com.cqut.compusEC.service.ShoppingCardService;
import com.cqut.compusEC.util.EntityIDFactory;

@Service("shoppingCardService")
public class ShoppingCardServiceImpl implements ShoppingCardService{

	@Resource(name = "entityDao")
	private EntityDao entityDao;

	@Override
	public Map<String, Object> addGoodsToCardInfo(String goodsId,
			String userId, String goodsCardDes) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(goodsId==null || goodsId.equals("") || userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "添加商品到购物车，传入参数为空");
		}else{
			String cardCondition = " 1=1 AND USER_ID='" + userId + "' ";
			List<TbShoppingCard> cardList = entityDao.getByCondition(cardCondition, TbShoppingCard.class);
			if(cardList==null || cardList.size()<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "添加商品到购物车，用户没有购物车");
			}else{
				String goodsCardCondition = " 1=1 AND CARD_ID='" + cardList.get(0).getCARD_ID() + "' AND GOODS_ID='" + goodsId + "' ";
				List<TbGoodsCard> goodsCards = entityDao.getByCondition(goodsCardCondition, TbGoodsCard.class);
				if(goodsCards==null || goodsCards.size()<=0){
					TbGoodsCard goodsCard = new TbGoodsCard();
					goodsCard.setGOODS_CARD_ID(EntityIDFactory.createId());
					goodsCard.setCARD_ID(cardList.get(0).getCARD_ID());
					goodsCard.setGOODS_ID(goodsId);
					if(goodsCardDes==null || goodsCardDes.equals("")){
						goodsCard.setGOODS_CARD_DES("");
					}else{
						goodsCard.setGOODS_CARD_DES(goodsCardDes);
					}
					int resultInt = entityDao.save(goodsCard);
					if(resultInt>0){
						resultMap.put("resultCode", "0000");
						resultMap.put("resultMessage", "添加商品到购物车成功");
					}else{
						resultMap.put("resultCode", "9999");
						resultMap.put("resultMessage", "添加商品到购物车失败");
					}
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "添加商品到购物车失败，已经添加过了");
				}
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteGoodsFromCardInfo(String goodsIds,
			String userId) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(goodsIds==null || goodsIds.equals("") || userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "删除购物车中的指定商品，传入参数为空");
		}else{
			String cardCondition = " 1=1 AND USER_ID='" + userId + "' ";
			List<TbShoppingCard> card = entityDao.getByCondition(cardCondition, TbShoppingCard.class);
			if(card==null || card.size() <= 0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "删除购物车中的指定商品，用户没有购物车");
			}else{
				String deleteCondition = " 1=1 AND CARD_ID='" + card.get(0).getCARD_ID() + "' AND GOODS_ID IN (" + goodsIds + ") ";
				
				int resultInt = entityDao.deleteByCondition(deleteCondition, TbGoodsCard.class);
				
				if(resultInt<0){
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "删除购物车中的指定商品失败");
				}else{
					resultMap.put("resultCode", "9999");
					resultMap.put("resultMessage", "删除购物车中的指定商品成功");
				}
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsFromCardInfo(String userId, String type,
			String page) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "获取购物车中的商品信息，传入参数为空");
		}else{
			int startIndex = 0;
			if(page==null || page.equals("")){
			}else{
				startIndex = Integer.parseInt(page)*6;
			}
			String[] properties = new String[]{
					"tb_goods.GOODS_ID",
					"tb_goods.GOODS_TITLE",
					"tb_goods.GOODS_DES",
					"tb_goods.GOODS_ORIGINAL",
					"tb_goods.GOODS_RESALE",
					"tb_goods.GOODS_CONDITION",
					"tb_goods.GOODS_CREATEDTIME",
					"tb_goods.GOODS_RECENTACCESS",
					"tb_goods.GOODS_VIEWCONTS",
					"tb_goods.GOODS_DINGNUM",
					"tb_goods.GOODS_CAINUM",
					"tb_goods.GOODS_STATUS",
					"tb_goods.LIST_ID",
					"(SELECT tb_file.FILE_URL FROM tb_file WHERE tb_file.GOODS_ID=tb_goods_card.GOODS_ID AND tb_file.FILE_TYPE=0 LIMIT 0,1) FILE_URL"
			};
			String table = "tb_shoppingcard,tb_goods_card,tb_goods";
			String condition = " 1=1 "
					+ " AND tb_shoppingcard.CARD_ID = tb_goods_card.CARD_ID "
					+ " AND tb_goods_card.GOODS_ID = tb_goods.GOODS_ID "
					+ " AND tb_shoppingcard.USER_ID='" + userId + "' ";
			if(type==null || type.equals("")){
			}else{
				condition += " AND tb_goods.GOODS_STATUS=" + type;
			}
			condition += " LIMIT " + startIndex + ",6 ";
			List<Map<String, Object>> resultList = entityDao.searchForeign(properties, table, null, null, condition);
			if(resultList==null || resultList.size()<=0){
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "获取购物车中的商品信息失败");
			}else{
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "获取购物车中的商品信息成功");
				resultMap.put("rows", resultList);
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getGoodsTotalFromCardInfo(String userId,
			String type) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		if(userId==null || userId.equals("")){
			resultMap.put("resultCode", "9999");
			resultMap.put("resultMessage", "根据用户获取购物车商品信息总数，传入参数为空");
		}else{
			try {
				String table = "tb_shoppingcard,tb_goods_card,tb_goods";
				String condition = " 1=1 AND tb_shoppingcard.CARD_ID=tb_goods_card.CARD_ID AND tb_goods_card.GOODS_ID=tb_goods.GOODS_ID AND tb_shoppingcard.USER_ID='" + userId + "' ";
				if(type==null || type.equals("")){
				}else{
					condition += " AND tb_goods.GOODS_STATUS='" +type + "' ";				
				}
				int total = entityDao.searchForeign(new String[]{"*"}, table, null, null, condition).size();
				int pageNum = (total/6) + 1;
				
				resultMap.put("resultCode", "0000");
				resultMap.put("resultMessage", "根据用户获取购物车商品信息总数成功");
				resultMap.put("pageNum", pageNum);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				resultMap.put("resultCode", "9999");
				resultMap.put("resultMessage", "根据用户获取购物车商品信息总数异常");
			}
		}
		return resultMap;
	}
}
