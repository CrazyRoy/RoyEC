package com.cqut.compusEC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqut.compusEC.service.GoodsService;

@Controller
@RequestMapping("/goodsController")
public class GoodsController {
	@Resource
	private GoodsService goodsService;

	// 新增商品
	@ResponseBody
	@RequestMapping("/addGoods")
	public JSONObject addGoods(String userId, String goodsTitle,String goodsDes, String goodsOrignal, String goodsResale, String goodsCondition, String listId, String fileNames, String filePaths){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = goodsService.insertGoodsInfo(userId, goodsTitle, goodsDes, goodsOrignal, goodsResale, goodsCondition, listId, fileNames, filePaths);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "新增商品异常");
		}
		return JSONObject.fromObject(result);
	}
	
	// 转卖商品
	@ResponseBody
	@RequestMapping("/insertGoodsByGoodsId")
	public JSONObject insertGoodsByGoodsId(String goodsId, String userId){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = goodsService.insertGoodsByGoodsId(goodsId, userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "转卖商品异常");
		}
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getGoodsList")  
    @ResponseBody
	public JSONObject getGoodsList(int limit, int offset, String order, String sort){
		Map<String, Object> result = goodsService.getGoodsList(limit, offset, sort, order);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getGoodsListByGoodsId")  
    @ResponseBody
	public JSONObject getGoodsListByGoodsId(String goodsId){
		Map<String, Object> result = goodsService.getGoodsListByGoodsId(goodsId);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/getGoodsListBySearch")  
    @ResponseBody
	public JSONObject getGoodsListBySearch(String seachContent, String page, String number){
		Map<String, Object> result = goodsService.getGoodsListBySearch(seachContent, page, number);
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/editGoodsInfo")  
    @ResponseBody
	public JSONObject editGoodsInfo(String goodsId, String goodsTitle,String goodsDes, String goodsOrignal, String goodsResale, String goodsCondition, String listId, String goodsRecentAccess, String goodsViewConts, String goodsDingNum, String goodsCaiNum, String goodsStatus, String fileNames, String filePaths){
		Map<String, Object> result = new HashMap<>();
		try {
			result = goodsService.editGoodsInfo(goodsId, goodsTitle, goodsDes, goodsOrignal, goodsResale, goodsCondition, listId, goodsRecentAccess, goodsViewConts, goodsDingNum, goodsCaiNum, goodsStatus, fileNames, filePaths);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "修改商品信息异常");
		}
		
		return JSONObject.fromObject(result);
	}
	
	@RequestMapping("/deleteGoods")  
    @ResponseBody
	public JSONObject deleteGoods(String goodsIds){
		Map<String, Object> result = new HashMap<>();
		try {
			result = goodsService.deleteGoods(goodsIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("resultCode", "9999");
			result.put("resultMessage", "删除商品信息异常");
		}
		
		return JSONObject.fromObject(result);
	}
	
    @RequestMapping("/getGoodsListByCreatetime")  
    @ResponseBody
    public JSONObject getGoodsListByCreatetime(String index){
        Map<String, Object> result = goodsService.getGoodsListByCreatetime(index);
        return JSONObject.fromObject(result);
    }

    @RequestMapping("/getGoodsListBySchoole")  
    @ResponseBody
    public JSONObject getGoodsListBySchoole(String userId, String index){
        Map<String, Object> result = goodsService.getGoodsListBySchoole(userId, index);
        return JSONObject.fromObject(result);
    }
    
    @RequestMapping("/getGoodsListByGuess")  
    @ResponseBody
    public JSONObject getGoodsListByGuess(){
        Map<String, Object> result = goodsService.getGoodsListByGuess();
        return JSONObject.fromObject(result);
    }
    
    @RequestMapping("/getGoodsListByListType")  
    @ResponseBody
    public JSONObject getGoodsListByListType(String typeId, String page, String number){
        Map<String, Object> result = goodsService.getGoodsListByListType(typeId, page, number);
        return JSONObject.fromObject(result);
    }
    
    @RequestMapping("/getGoodsTotalByListType")  
    @ResponseBody
    public JSONObject getGoodsTotalByListType(String typeId, String number){
        Map<String, Object> result = goodsService.getGoodsTotalByListType(typeId, number);
        return JSONObject.fromObject(result);
    }
    
    @RequestMapping("/getGoodsListByListViewConts")  
    @ResponseBody
    public JSONObject getGoodsListByListViewConts(){
        Map<String, Object> result = goodsService.getGoodsListByListViewConts();
        return JSONObject.fromObject(result);
    }
    
    @RequestMapping("/getGoodsListByListId")  
    @ResponseBody
    public JSONObject getGoodsListByListId(String listId, String page){
        Map<String, Object> result = goodsService.getGoodsListByListId(listId, page);
        return JSONObject.fromObject(result);
    }
    
    @RequestMapping("/getGoodsTotalByListId")  
    @ResponseBody
    public JSONObject getGoodsTotalByListId(String listId){
        Map<String, Object> result = goodsService.getGoodsTotalByListId(listId);
        return JSONObject.fromObject(result);
    }
    
    @RequestMapping("/getGoodsListByUserId")  
    @ResponseBody
    public JSONObject getGoodsListByUserId(String userId, String page, String type){
        Map<String, Object> result = goodsService.getGoodsListByUserId(userId, page, type);
        return JSONObject.fromObject(result);
    }
    
    @RequestMapping("/getGoodsTotalByUserId")  
    @ResponseBody
    public JSONObject getGoodsTotalByUserId(String userId, String type){
        Map<String, Object> result = goodsService.getGoodsTotalByUserId(userId, type);
        return JSONObject.fromObject(result);
    }
}
