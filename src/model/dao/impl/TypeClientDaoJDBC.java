package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.TypeClientDao;
import models.entities.TypeClient;

public class TypeClientDaoJDBC implements TypeClientDao{
	
	private Connection conn;
	
	public TypeClientDaoJDBC(Connection conn) {
		this.conn =  conn;
	}

	@Override
	public void insert(TypeClient obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TypeClient obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TypeClient findById(Integer id) {
	
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//preparando a query
			st = conn.prepareStatement(					
					"select * from typeclient where id = ?"				
					);
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				TypeClient typeClient = new TypeClient();
				typeClient.setId(rs.getInt("id"));
				typeClient.setType(rs.getString("type"));
				
				return typeClient;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public TypeClient deleteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeClient> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"select * from typeclient"					
					);
			
			rs = st.executeQuery();
			
			List<TypeClient> list = new ArrayList<>();
			
			while (rs.next()) {				
				TypeClient typeClient = new TypeClient();				
				typeClient.setId(rs.getInt("id"));
				typeClient.setType(rs.getString("type"));
				list.add(typeClient);								
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	
}
