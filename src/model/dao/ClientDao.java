package model.dao;

import java.util.List;

import models.entities.Client;

public interface ClientDao {
	
	void insert(Client obj);
	void update(Client obj);
	void findById(Integer id);
	Client deleteById(Integer id);
	List<Client> findAll();
	
}
