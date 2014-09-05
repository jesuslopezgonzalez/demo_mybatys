package com.intelygenz.psg01.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GenericMapper <T, PK extends Serializable>{

	List<T> getAll();
	
	T findById(int id);
	
	void update(T object);
	
	void remove(T object);
	
	void insert(T object);
	
	List<T> getPaginated(@Param("startPosition")int startPosition, @Param("maxResult")int maxResult);
}