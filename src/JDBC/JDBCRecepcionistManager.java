package JDBC;

import POJO.Client;
import Interfaces.RecepcionistManager;
import POJO.Recepcionist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class JDBCRecepcionistManager implements RecepcionistManager {

	private JDBCManager manager;
	
	public JDBCRecepcionistManager(JDBCManager m) {
		
		this.manager=m;
	}
	
	@Override
	public void addClient(Client c) throws Exception {
		
	
		String sql= "INSERT INTO client (id, name, health_number, address, email) "
			+ " VALUES(?,?,?,?,?)";
	
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setString(1, c.getId());
		prep.setString(2, c.getName());
		prep.setString(3, c.getHealthNumber());
		prep.setString(4, c.getAddress());
		prep.setString(5, c.getEmail());

		prep.executeUpdate();
		
	}
	
	@Override
	public void deleteClient(int clientId) throws Exception{
		
		String sql= "DELETE FROM client "
				+ " WHERE id= ?";
		
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, clientId);

		prep.executeUpdate();
	
	}
	
	@Override
	public void updateClient(Client c) throws Exception{
		
		String sql="UPDATE client "
				+ "SET name= ?, health_number=?, address=?, email=?"
				+ "WHERE id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1, c.getName());
		prep.setInt(2, c.getHealthNumber());
		prep.setString(3, c.getAddress());
		prep.setString(4, c.getEmail());
		prep.setInt(5, c.getId());

		prep.executeUpdate();
		
	}
	
	@Override
	public List<Client> listAllClients() throws Exception{
		
		List <Client> clients= new LinkedList<>();
		
		Statement stmt= this.manager.getConnection().createStatement();	
		
		String sql="SELECT * FROM client";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()){
			
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int healthNumber = rs.getInt("health number");
			String address = rs.getString("address");
			String email = rs.getString("email");
			
			Client client = new Client(id, name, healthNumber, address, email);
			
			clients.add(client);
		}
		
		rs.close();
		stmt.close();

		return clients;
		
	}

	
}
