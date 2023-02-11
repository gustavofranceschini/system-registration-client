package model.dao;

import db.DB;
import model.dao.impl.ClientDaoJDBC;
import model.dao.impl.TypeClientDaoJDBC;

public class DaoFactory {
	
	public static ClientDao createClientDao() {
		return new ClientDaoJDBC(DB.getConnection());
	}
	
	public static TypeClientDao createTypeClientDao() {
		return new TypeClientDaoJDBC(DB.getConnection());
	}
}
