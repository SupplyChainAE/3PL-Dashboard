package com.snapdeal.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.snapdeal.entity.BaseEntity;
@Repository
public interface EntityDao {

	public <T extends BaseEntity> void save(T object);
	public <T extends BaseEntity> void update(T object);
	public <T extends BaseEntity> void delete(T object);
	public <T extends BaseEntity> T findById(Class<T> objectClass,Long id);
	public <T extends BaseEntity> List<T> findAll(Class<T> objectClass);
	public EntityManager getEntityManager();
	public <T extends BaseEntity> void saveOrUpdate(T object);
	public void flush();
	public void clear();
}
