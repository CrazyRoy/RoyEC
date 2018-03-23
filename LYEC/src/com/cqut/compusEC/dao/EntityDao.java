package com.cqut.compusEC.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqut.compusEC.entity.Entity;
import com.cqut.compusEC.util.TableCreator;

/**
 * 集成实体DAO
 * @author Ly
 *
 */
@Service
public class EntityDao {
	@Resource(name = "baseEntityDao")
	BaseEntityDao baseDao;

	@Resource(name = "baseSearchDao")
	BaseSearchDao searchDao;

	public int save(Entity entity) {
		// TODO Auto-generated method stub
		return baseDao.save(entity);
	}

	public int saveEntities(String tableName, String entityProperties, String entityValues) {
		// TODO Auto-generated method stub
		return baseDao.saveEntities(tableName, entityProperties, entityValues);
	}

	public int updatePropByCondition(Entity entity, String condition) {
		// TODO Auto-generated method stub
		return baseDao.updatePropByCondition(entity, condition);
	}

	public int updatePropByID(Entity entity, String ID) {
		// TODO Auto-generated method stub
		return baseDao.updatePropByID(entity, ID);
	}

	public <T extends Entity> int deleteByID(String ID, Class<T> t) {
		return baseDao.deleteByID(ID, Entity.getTableName(t),
				Entity.getPrimaryKey(t));
	}

	public <T extends Entity> int deleteByCondition(String condition, Class<T> t) {
		return baseDao.deleteByCondition(condition, Entity.getTableName(t));
	}

	public <T extends Entity> int deleteEntities(String[] IDs, Class<T> t) {
		return baseDao.deleteEntities(IDs, Entity.getTableName(t),
				Entity.getPrimaryKey(t));
	}

	public <T extends Entity> T getByID(String ID, Class<T> t) {
		Map<String, Object> properties = baseDao.getByID(ID,
				Entity.getPrimaryKey(t), Entity.getTableName(t));
		T entity = null;
		try {
			entity = t.newInstance();
			entity.setProperties(properties);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

	public <T extends Entity> List<T> getByCondition(String condition,
			Class<T> t) {
		List<Map<String, Object>> list = baseDao.getByCondition(condition,
				Entity.getTableName(t));
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> temp : list) {
			T entity = null;
			try {
				entity = t.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			entity.setProperties(temp);
			result.add(entity);
		}
		return result;
	}

	public <T extends Entity> Map<String, Object> findByID(String[] properties,
			String id, Class<T> t) {
		return baseDao.findByID(properties, id, Entity.getPrimaryKey(t),
				Entity.getTableName(t));
	}

	public <T extends Entity> List<Map<String, Object>> findByCondition(
			String[] properties, String condition, Class<T> t) {
		return baseDao.findByCondition(properties, condition,
				Entity.getTableName(t));
	}

	public <T extends Entity> int getCountByCondition(String condition,
			Class<T> t) {
		return baseDao.getCountByCondition(condition, Entity.getPrimaryKey(t),
				Entity.getTableName(t));
	}

	public void createtable(String tableName, Set<String> fieldSqls) {
		baseDao.createTable(tableName, fieldSqls);
	};

	public List<Map<String, Object>> searchForeign(String[] properties,
			String baseEntity, String joinEntity, String[] foreignEntitys,
			String condition) {
		List<Map<String, Object>> result;
		result = searchDao.searchForeign(properties, baseEntity, joinEntity,
				foreignEntitys, null, condition);
		return result;
	}

	public int getForeignCount(String primaryKey, String baseEntity,
			String joinEntity, String[] foreignEntity, String condition) {
		return searchDao.getForeignCount(primaryKey, baseEntity, joinEntity,
				foreignEntity, null, condition);
	}

	public List<Map<String, Object>> searchWithpaging(String[] properties,
			String baseEntity, String joinEntity, String[] foreignEntitys,
			String condition, String groupField, String orderField,
			String sortMode, int pageNum, int pageIndex) {

		int startIndex = pageNum * (pageIndex - 1);
		List<Map<String, Object>> result;

		if (orderField == null)
			orderField = TableCreator.getPrimaryKey(baseEntity);

		if (sortMode == null)
			sortMode = "desc";

		result = searchDao.searchWithpagingInMysql(properties, baseEntity,
				joinEntity, foreignEntitys, null, condition, groupField,
				orderField, sortMode, startIndex, pageNum);
		return result;
	}
}