package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.ClientDao;
import models.entities.Client;
import models.entities.TypeClient;

public class ClientDaoJDBC implements ClientDao {
	
	private Connection conn;
	
	public ClientDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Client client) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"insert into client "
					+ "(name, rg, cpf, birthDate, idTypeClient) "
					+ "values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS					
					);
			
			st.setString(1, client.getName());
			st.setInt(2, client.getRg());
			st.setInt(3, client.getCpf());
			st.setDate(4, new java.sql.Date(client.getBirthDate().getTime()));
			st.setInt(5, client.getTypeClient().getId());
			
			int rowsAffected = st.executeUpdate();
			//Foi aqui que eu parei
			
			if (rowsAffected > 0 ) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					client.setId(id);
				} else {
					throw new DbException("Unexpected Error! No rows affected!");
				}
				DB.closeResultSet(rs);
			}			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Client client) {
		
		PreparedStatement st = null;
		
		
		
	}	

	@Override
	public Client findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"select \r\n"
					+ "c.*,tp.type\r\n"
					+ "from typeclient as tp\r\n"
					+ "inner join client as c on tp.id = c.idTypeClient "
					+ "where c.id = ?"
					);
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				TypeClient tp = instantiateTypeClient(rs);
				Client client = instantiateClient(rs, tp);
				return client;
			}
			return null;		
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}
	
	private Client instantiateClient(ResultSet rs, TypeClient typeClient) throws SQLException {
		Client client = new Client();
		client.setId(rs.getInt("id"));
		client.setName(rs.getString("name"));
		client.setRg(rs.getInt("rg"));
		client.setCpf(rs.getInt("cpf"));
		client.setBirthDate(rs.getDate("birthDate"));
		client.setTypeClient(typeClient);
		return client;
	}
	
	private TypeClient instantiateTypeClient(ResultSet rs) throws SQLException {
		TypeClient typeClient = new TypeClient();
		typeClient.setId(rs.getInt("id"));
		typeClient.setType(rs.getString("type"));
		return typeClient;
	}

	@Override
	public void deleteById(Integer id) {
		 PreparedStatement st = null;
		 
		 try {
			 st = conn.prepareStatement(
					 
					 "delete from client where id = ?"
					 );
			 
			 st.setInt(1, id);
			 
			 int rowsAffected = st.executeUpdate();
			 
			 if (rowsAffected == 0) {
				 throw new DbException("ID Not existing!");
			 }
		 }
		 catch (SQLException e) {
			 throw new DbException(e.getMessage());
		 }
		 catch (ConcurrentModificationException e) {
			 throw new DbException(e.getMessage());
		 }
		 finally {
			 DB.closeStatement(st);
		 }
	}

	@Override
	public List<Client> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st  =conn.prepareStatement(
					
					"select \r\n"
					+ "c.*,tp.*\r\n"
					+ "from typeclient as tp\r\n"
					+ "inner join client as c on tp.id = c.idTypeClient "
					+ "order by c.id"					
					);
			
			rs = st.executeQuery();
			
			List<Client> list = new ArrayList<>();
			
			Map<Integer, TypeClient> map = new HashMap<>();
			
			while (rs.next()) {
				TypeClient typeClient = map.get(rs.getInt("id"));
				
				if (typeClient == null) {
					typeClient = instantiateTypeClient(rs);
					//Guardar a informação no map
					map.put(rs.getInt("id"), typeClient);
				}
				
				Client client = instantiateClient(rs, typeClient);
				list.add(client);
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
