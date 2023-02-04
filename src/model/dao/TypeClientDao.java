package model.dao;

import java.util.List;

import models.entities.TypeClient;

public interface TypeClientDao {
	
	void insert(TypeClient obj);
	void update(TypeClient obj);
	void findById(Integer id);
	TypeClient deleteById(Integer id);
	List<TypeClient> findAll();
}
