package com.snapdeal.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.entity.BaseEntity;


@Named("entityDao")
@Transactional
public class EntityDaoImpl implements EntityDao {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public <T extends BaseEntity> void save(T object) {
		entityManager.persist(object);
	}

	@Override
	public <T extends BaseEntity> void update(T object) {
		entityManager.merge(object);
	}

	@Override
	public <T extends BaseEntity> void delete(T object) {
		entityManager.remove(object);
	}

	@Override
	public <T extends BaseEntity> void saveOrUpdate(T object) {
		if(object.getId() != null)
		{	
			entityManager.merge(object);
		}
		else {
			entityManager.persist(object);	
		}
	}
	
	@Override
	public <T extends BaseEntity> T findById(Class<T> objectClass, Long id) {
		T object = null;
		try {
			object = objectClass.newInstance();
			object = entityManager.find(objectClass, id);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BaseEntity> List<T> findAll(Class<T> objectClass) {
		Query query = entityManager.createQuery("Select data from "+objectClass.getName()+" data");
		List<T> objectList = (List<T>)query.getResultList();
		return objectList;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	@Override
	public void flush(){
		entityManager.flush();
	}

	@Override
	public void clear(){
		entityManager.clear();
	}

}
