package JDBC;

import Interfaces.ClientManager;

import POJO.Appointment;
import POJO.Client;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class JDBCClientManager implements ClientManager {

	private JDBCManager manager;
	
	public JDBCClientManager(JDBCManager m) {
		
		this.manager=m;
	}
	
	@Override
	public Client getClientById(Integer clientId) throws Exception{
		
		Client client=null;
		
		String sql= "SELECT * FROM client WHERE pat_id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1, clientId);
				
		ResultSet rs= prep.executeQuery();
		
		if(rs.next()) {
			
			String name= rs.getString("pat_name");
			Integer healthNumber= rs.getInt("hum");
			String address= rs.getString("pat_address");
			String email= rs.getString("pat_email");
			
			client= new Client(clientId, name, healthNumber, address, email);
			
		}

		rs.close();
		prep.close();
		
		return client;
		
	
	}

	@Override
	public void createClient(Client client) throws Exception {
		// TODO Auto-generated method stub
		

		String sql= "INSERT INTO client (pat_name,hum,pat_address,pat_email) VALUES (?,?,?,?)";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setString(1, client.getPat_name());
			prep.setInt(2, client.getHum());
			prep.setString(3, client.getPat_address());
			prep.setString(4, client.getPat_email());
			prep.executeUpdate();
		
	}

	@Override
	public void deleteClient(Integer pat_id) throws Exception {
		// TODO Auto-generated method stub
		String sql= "DELETE FROM client "
				+ " WHERE pat_id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, pat_id);

		prep.executeUpdate();
	}

	@Override
	public void updateClient(Client client) throws Exception {
		// TODO Auto-generated method stub
		
		String sql="UPDATE client "
				+ "SET pat_name= ?, hum=?, pat_address=?, pat_email=?"
				+ "WHERE pat_id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1, client.getPat_name());
		prep.setInt(2,client.getHum());
		prep.setString(3,client.getPat_address());
		prep.setString(4,client.getPat_email());

		prep.executeUpdate();
		
	}

	@Override
	public ArrayList<Client> getListAllClients() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Client> all = new ArrayList<Client>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM client";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				   Integer id= rs.getInt("pat_id");  
				   String name=  rs.getString("pat_name");
				   Integer num =  rs.getInt("hum");
				   String  add= rs.getString("pat_address");
				   String email=  rs.getString("pat_email");
				   
				   
				 Client c = new Client(id,name,num,add,email);
				  all.add(c);
			}
			
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return all;
	}

	@Override
	public ArrayList<Appointment> getAllAppFromClient(Integer clientId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList <Appointment> appFromClient= new  ArrayList<Appointment>();
		JDBCAppointmentManager app= new JDBCAppointmentManager(manager.getConnection());
		try {
			String sql = "SELECT app_id FROM client LEFT JOIN appointment ON pat_id=patient_id WHERE pat_id= ? GROUP BY pat_id";
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			prep.setInt(1, clientId);
			ResultSet rs = prep.executeQuery(sql);
			
			while(rs.next())
			{
				Integer appId= rs.getInt("app_id");
				Appointment appointment=app.viewAppointment(appId);
			    appFromClient.add(appointment);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return appFromClient;
	}

	@Override
	public Client getClientByName(String clientName) throws Exception {
		// TODO Auto-generated method stub
        Client cli= null;
		
		String sql= "SELECT * FROM client WHERE pat_name= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		prep.setString(1,clientName);
				
		ResultSet rs= prep.executeQuery(sql);
		
		while(rs.next()) {
			
			Integer id= rs.getInt("pat_id");
			Integer hum= rs.getInt("hum");
			String address= rs.getString("pat_address");
			String email= rs.getString("pat_email");
			
			cli= new Client(id, clientName, hum, address, email);
			
		}

		rs.close();
		prep.close();
		
		return cli;
	}
}
