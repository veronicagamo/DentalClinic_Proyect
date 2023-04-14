package JDBC;

import Interfaces.ClientManager;
import POJO.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class JDBCClientManager implements ClientManager {

	private JDBCManager manager;
	
	public JDBCClientManager(JDBCManager m) {
		
		this.manager=m;
	}
	
	@Override
	public Client getClientById(int clientId) throws Exception{
		
		Client client=null;
		
		String sql= "SELECT * FROM client"
				+ "WHERE id=?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1, clientId);
				
		ResultSet rs= prep.executeQuery();
		
		if(rs.next()) {
			
			int id=rs.getInt("id");
			String name= rs.getString("name");
			int healthNumber= rs.getInt("health number");
			String address= rs.getString("address");
			String email= rs.getString("email");
			
			client= new Client(id, name, healthNumber, address, email, null);
			
		}

		rs.close();
		prep.close();
		
		return client;
		
	
	}
}
