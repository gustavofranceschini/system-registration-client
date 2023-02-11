package model.dao;

import java.util.List;

import models.entities.TypeClient;

public interface TypeClientDao {
	
	void insert(TypeClient typeClient);
	void update(TypeClient typeClient);
	void deleteById(Integer id);
	TypeClient findById(Integer id);	
	List<TypeClient> findAll();
}
