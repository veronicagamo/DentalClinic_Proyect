package JDBC;

import Interfaces.ClientManager;



import POJO.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exceptions.IdNotFoundException;
import Exceptions.NameNotFoundException;



public class JDBCClientManager implements ClientManager {

	private JDBCManager manager;
	
	public JDBCClientManager(JDBCManager m) {
		
		this.manager=m;
	}
	
	@Override
	public Client getClientById(Integer clientId){
		
		Client client=null;
		PreparedStatement prep=null;
		ResultSet rs= null;
		
		try {
			
			String sql= "SELECT * FROM client WHERE pat_id= ?";
		
		    prep= manager.getConnection().prepareStatement(sql);
		
		    prep.setInt(1, clientId);
				
		    rs= prep.executeQuery();
		
			while(rs.next()) {
				
				String name= rs.getString("pat_name");
				Integer healthNumber= rs.getInt("hum");
				String address= rs.getString("pat_address");
				String email= rs.getString("pat_email");
				
				client= new Client(clientId, name, healthNumber, address, email);
				
			}
	        if(client==null) {
				
				throw new IdNotFoundException(" \nThe specified id does not correspond to any of the ids "
						+ "of the clients");
		   }
		}
		catch(SQLException e) {
			
			System.out.println("\nThe requested client can not be returned "+e);
			e.printStackTrace();


		}catch(IdNotFoundException e) {
			
			System.out.println(e);

			
		}finally {
			
			try {
				
				if(rs!=null) {
					rs.close();
				}
				if(prep!=null) {
					prep.close();
				}    	
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}
		}	
		
		return client;
	
	}

	@Override
	public void createClient(Client client){
		// TODO Auto-generated method stub
		

		try {
			
			String sql= "INSERT INTO client (pat_name,hum,pat_address,pat_email) VALUES (?,?,?,?)";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setString(1, client.getPat_name());
			prep.setInt(2, client.getHum());
			prep.setString(3, client.getPat_address());
			prep.setString(4, client.getPat_email());
//			File photo = new File("./Photo/" + fileName);
//			InputStream streamBlob = new FileInputStream(photo);
//			byte[] bytesBlob = new byte[streamBlob.available()];
//			streamBlob.read(bytesBlob);
//			streamBlob.close();
//    		prep.setBytes(6, bytesBlob);
     		prep.executeUpdate();
		}
		catch(SQLException e) {
			
			System.out.println(" \nThe client has not been created successfully "+e);
			e.printStackTrace();


		}
		
	}

	@Override
	public void deleteClient(Integer pat_id){
		// TODO Auto-generated method stub
		
		try {
			
			String sql= "DELETE FROM client "
				+ " WHERE pat_id= ?";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
			prep.setInt(1, pat_id);

			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException(" \nThe specified id does not correspond to any of the ids "
						+ "of the clients");
			}	
		}
		catch(SQLException e) {
			
			System.out.println(" \nThe requested client has not been deleted successfully "+e);
			e.printStackTrace();


		}
		catch(IdNotFoundException e) {
			
			System.out.println(e);
		}
		
	}

	@Override
	public void updateClient(Client client){
		// TODO Auto-generated method stub
		
		try {
			
			String sql="UPDATE client "
				+ "SET pat_name= ?, hum= ?, pat_address= ?, pat_email= ? WHERE pat_id= ?";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setString(1, client.getPat_name());
			prep.setInt(2,client.getHum());
			prep.setString(3,client.getPat_address());
			prep.setString(4,client.getPat_email());
			prep.setInt(5,client.getPat_id());

			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException(" \nThe specified id does not correspond to any of the ids "
						+ "of the clients");
			}	
		}catch(SQLException e) {
			
			System.out.println("The requested client has not been updated successfully "+e);
			e.printStackTrace();

		}
		catch(IdNotFoundException e) {
			
			System.out.println(e);
		}
		
		
		
	}

	@Override
	public ArrayList<Client> getListAllClients(){
		// TODO Auto-generated method stub
		ArrayList<Client> all = new ArrayList<Client>();
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM client";
			rs = stmt.executeQuery(sql);
			
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
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}    	
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}
		
		}
		
		return all;
	}

	@Override
	public ArrayList<Appointment> getAllAppFromClient(Integer clientId){
		// TODO Auto-generated method stub
		ArrayList <Appointment> appFromClient= new  ArrayList<Appointment>();
		JDBCAppointmentManager app= new JDBCAppointmentManager(manager);
		try {
			String sql = "SELECT app_id FROM client LEFT JOIN appointment ON pat_id=client_id WHERE pat_id= ?";
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			prep.setInt(1, clientId);
			ResultSet rs = prep.executeQuery();
			Appointment appointment=null;
			
			while(rs.next())
			{
				Integer appId= rs.getInt("app_id");
				 appointment=app.viewAppointment(appId);
			    appFromClient.add(appointment);
			    
				
			}
			if(appFromClient.size()==0) {
				
				throw new IdNotFoundException("\nThe specified id does not correspond to any of the ids "
						+ "of the clients");

			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return appFromClient;
	}

	@Override
	public Client getClientByName(String clientName){
		// TODO Auto-generated method stub
        Client client=null;
        PreparedStatement prep=null;
  		ResultSet rs= null;
		
  		try {
  			
  			String sql= "SELECT * FROM client WHERE pat_name= ?";
		
			prep= manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,clientName);
					
			rs= prep.executeQuery();
			
			while (rs.next()) {
				Integer clientId = rs.getInt("pat_id");
				String name= rs.getString("pat_name");
				Integer healthNumber= rs.getInt("hum");
				String address= rs.getString("pat_address");
				String email= rs.getString("pat_email");
				
				client= new Client(clientId, name, healthNumber, address, email);
				
			}
			if(client==null) {
				
				throw new NameNotFoundException("\nThe specified name does not correspond to any of the names "
						+ "of the clients");
		   }
		}
  		catch(SQLException e) {
			e.printStackTrace();
		}
  		catch(NameNotFoundException e) {
			
			System.out.println(e);	
		}
		finally {
			
			try {
				
				if(rs!=null) {
					rs.close();
				}
				if(prep!=null) {
					prep.close();
				}    	
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}
		
		}
		
		return client;
		
	}

	@Override
	public Client getClientByEmail(String clientEmail) throws Exception {
		// TODO Auto-generated method stub
		Client client= null;
String sql= "SELECT * FROM client WHERE pat_email= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1,clientEmail);
				
		ResultSet rs= prep.executeQuery();
		
		while(rs.next()) {
			
			Integer clientId = rs.getInt("pat_id");
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
}
