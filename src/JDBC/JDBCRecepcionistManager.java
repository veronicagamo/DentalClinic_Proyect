package JDBC;


import Interfaces.RecepcionistManager;
import POJO.Client;
import POJO.Dentists;
import POJO.Receptionist;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exceptions.IdNotFoundException;
import Exceptions.NameNotFoundException;

public class JDBCRecepcionistManager implements RecepcionistManager {

	private JDBCManager manager;
	
	public JDBCRecepcionistManager(JDBCManager m) {
		
		this.manager=m;
	}
	
	//The id variable is not included as a parameter in  INSERT INTO because it is
	//generated by the database with an AUTOINCREMENT
	@Override
	public void addReceptionist(Receptionist rec){
		
	
		try {
			
			String sql= "INSERT INTO receptionist (name,bank_account,email,mobile) "
			+ " VALUES(?,?,?,?)";
	
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setString(1, rec.getRep_name());
			prep.setInt(2, rec.getBank_account());
			prep.setString(3, rec.getRep_email());
			prep.setInt(4, rec.getRep_mobile());
	
			prep.executeUpdate();
		}
		catch(SQLException e) {
			
			System.out.println("The receptionist has not been added successfully "+e);
			e.printStackTrace();

		}
	}
	
	@Override
	public void deleteReceptionist(Integer ReceptionistId) throws SQLException{
		
		String sql= "DELETE FROM receptionist WHERE recep_id= ?";
		
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, ReceptionistId);

		prep.executeUpdate();
	
	}

	
	
	@Override
	public void updateReceptionist(Receptionist r){
		
		try {
			
			String sql="UPDATE receptionist"
				+ "SET name= ?, bank_account= ?, email= ?, mobile= ? WHERE recep_id= ?";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, r.getRep_name());
			prep.setInt(2, r.getBank_account());
			prep.setString(3, r.getRep_email());
			prep.setInt(4,r.getRep_mobile());
	
			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException("The specified id does not correspond to any of the ids"
						+ "of the receptionists");
			}
		}
		catch(SQLException e) {
			
			System.out.println("The requested receptionist has not been updated successfully "+e);
			e.printStackTrace();

		}
		catch(IdNotFoundException e) {
		
			System.out.println(e);
		}
		
		
	}
	
	@Override
	public ArrayList<Receptionist> listAllReceptionists(){
		
		ArrayList <Receptionist> receptionists= new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		Receptionist r=null;
		
		try {
			
			stmt= manager.getConnection().createStatement();	
		
			String sql="SELECT * FROM receptionist";
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				
				Integer id = rs.getInt("recep_id");
				String name = rs.getString("name");
				Integer bank = rs.getInt("bank_account");
				String email = rs.getString("email");
				Integer mobile = rs.getInt("mobile");
				
				r = new Receptionist(id, name, bank,email,mobile);
				
				receptionists.add(r);
			}
			
		}
		catch(SQLException e) {
			
			System.out.println("The receptionists can not be returned "+e);
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
		

		return receptionists;
		
	}

	@Override
	public Receptionist geRecepByName(String recepName){
		// TODO Auto-generated method stub
       Receptionist rec= null;
       PreparedStatement prep=null;
       ResultSet rs=null;
		
       try{
    	   
    	String sql= "SELECT * FROM receptionist WHERE name= ?";
		
		prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1,recepName);
				
		rs= prep.executeQuery();
		
		if(rs.next()) {
			
			Integer id = rs.getInt("recep_id");
			Integer bank = rs.getInt("bank_account");
			String email = rs.getString("email");
			Integer mobile = rs.getInt("mobile");
			
			rec= new Receptionist(id, recepName,bank,email, mobile);
			
		}
		else {
			
			throw new NameNotFoundException("The specified name does not correspond to any of the names"
					+ "of the receptionists");
		} 
		
       }
       catch(SQLException e) {
			
			System.out.println("The requested receptionist can not be returned "+e);
			e.printStackTrace();
			
		}catch(NameNotFoundException e) {
			
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
		
		return rec;
	}

	@Override
	public Receptionist getreceptionistById(Integer receptionistId){
		// TODO Auto-generated method stub

		Receptionist rec= null;
		PreparedStatement prep=null;
		ResultSet rs=null;
		
		try {
			
			String sql= "SELECT * FROM receptionist WHERE recep_id= ?";
		
			prep= manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1,  receptionistId);
					
			rs= prep.executeQuery();
			
			if(rs.next()) {
				
				String name = rs.getString("name");
				Integer bank = rs.getInt("bank_account");
				String email = rs.getString("email");
				Integer mobile = rs.getInt("mobile");
				
				rec= new Receptionist(receptionistId, name,bank,email, mobile);
				
			}
			else {
				
				throw new IdNotFoundException("The specified id does not correspond to any of the ids"
						+ "of the receptionists");
			}
		}
		catch(SQLException e) {
			
			System.out.println("The requested receptionist can not be returned "+e);
			e.printStackTrace();
			
		}catch(IdNotFoundException e) {
			
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
		
		return rec;
	}

	@Override
	public Receptionist getRecepByEmail(String recepEmail) throws Exception {
		// TODO Auto-generated method stub
		 Receptionist rec= null;
			
			String sql= "SELECT * FROM receptionist WHERE email= ?";
			
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,  recepEmail);
					
			ResultSet rs= prep.executeQuery();
			
			if(rs.next()) {
				Integer id = rs.getInt("recep_id");
				String name = rs.getString("name");
				Integer bank = rs.getInt("bank_account");
				Integer mobile = rs.getInt("mobile");
				
				rec= new Receptionist(id, name,bank,recepEmail, mobile);
				
			}

			rs.close();
			prep.close();
			
			return rec;
	}

	
}
