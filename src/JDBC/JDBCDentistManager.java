package JDBC;

import java.sql.PreparedStatement;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exceptions.IdNotFoundException;
import Exceptions.NameNotFoundException;
import Interfaces.DentistManager;
import POJO.Appointment;
import POJO.Dentists;

public class JDBCDentistManager implements DentistManager{

	private JDBCManager manager;
	
	public JDBCDentistManager(JDBCManager m) {
		
		this.manager=m;
	}

	@Override
	public void addDentist(Dentists d){
		
		try {
			String sql= "INSERT INTO dentist (name, bank_account, doc_email, doc_mobile) VALUES(?,?,?,?)";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setString(1, d.getName());
			prep.setInt(2, d.getBank_account());
			prep.setString(3, d.getDoc_email()); 
			prep.setString(4, d.getDoc_mobile());
			prep.executeUpdate();
			
		}
		catch(SQLException e) {
			
			System.out.println(" \nThe dentist has not been added successfully "+e);
			e.printStackTrace();

		}
		
	}

	@Override
	public void deleteDentist(Integer dentistId) {

		try {
			
			String sql= "DELETE FROM dentist "
				+ " WHERE doc_id= ?";
		
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
			prep.setInt(1, dentistId);
			
			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException(" \nThe specified id does not correspond to any of the ids "
						+ "of the dentists");
			}	
		}
		catch(SQLException e) {
			
			System.out.println("\nThe requested dentist has not been deleted successfully "+e);
			e.printStackTrace();

		}
		catch(IdNotFoundException e) {
			
			System.out.println(e);
		}
		
	}

	@Override
	public void updateDentist(Dentists d) { 
		
		try {
			
			String sql= "UPDATE dentist SET name= ?, bank_account= ?, doc_email= ?, doc_mobile= ? WHERE doc_id= ?";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, d.getName());
			prep.setInt(2, d.getBank_account());
			prep.setString(3, d.getDoc_email());
			prep.setString(4, d.getDoc_mobile());
			prep.setInt(5, d.getDoc_id());
			
			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException("\nThe specified id does not correspond to any of the ids "
						+ "of the dentists");
			}
		}
		catch(SQLException e) {
		
			System.out.println("\nThe requested dentist has not been updated successfully "+e);
			e.printStackTrace();
		}
		catch(IdNotFoundException e) {
		
			System.out.println(e);
		}
		

		
	}

	@Override
	public ArrayList<Dentists> listAllDentists(){

		ArrayList <Dentists> dentists= new ArrayList<>();
		Statement stmt=null;	
		ResultSet rs=null;

		try {
			
			stmt= manager.getConnection().createStatement();	
		
			String sql= "SELECT * FROM dentist";
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				
				Integer id = rs.getInt("doc_id");
				String name = rs.getString("name");
				Integer bankAccount = rs.getInt("bank_account");
				String email = rs.getString("doc_email");
				String mobile = rs.getString("doc_mobile");
				
				Dentists dentist = new Dentists (id, name, bankAccount, mobile, email);
				
				dentists.add(dentist);
			}
		}
		catch(SQLException e) {
			
			System.out.println("\nThe dentists can not be returned "+e);
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

		return dentists;
	}

	@Override
	public Dentists getDentistById(Integer id){


		Dentists dentist= null;
		PreparedStatement prep=null;
		ResultSet rs=null;
		
		try {
			
			String sql= "SELECT * FROM dentist WHERE doc_id= ?";
		
			prep= manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, id);
					
			rs= prep.executeQuery();
			
			while(rs.next()) {
				
				String name= rs.getString("name");
				Integer bankAccount= rs.getInt("bank_account");
				String mobile= rs.getString("doc_mobile");
				String email= rs.getString("doc_email");
				
				dentist= new Dentists(id, name, bankAccount,mobile, email);
				
			}
			
			if(dentist==null) {
				
				throw new IdNotFoundException("\nThe specified id does not correspond to any of the ids "
						+ "of the dentists");
			}
			
		}
		catch(SQLException e) {
			
			System.out.println("\nThe requested dentist can not be returned "+e);
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
		
		return dentist;
	}

	@Override
	public Dentists getDentistByName(String dentistName) {
	
		Dentists dentist= null;
		PreparedStatement prep=null;
		ResultSet rs=null;
		
		try {
			
			String sql= "SELECT * FROM dentist WHERE name=?";
		
			prep= manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,dentistName);
					
			rs= prep.executeQuery();
			
			while(rs.next()) {
			
			Integer id= rs.getInt("doc_id");
			String name= rs.getString("name");
			Integer bankAccount= rs.getInt("bank_account");
			String email= rs.getString("doc_email");
			String mobile= rs.getString("doc_mobile");
			
			dentist= new Dentists(id, name, bankAccount,mobile, email);
			
			}
			
			if(dentist==null) {
				
				throw new NameNotFoundException("\nThe specified name does not correspond to any of the names "
						+ "of the dentists");
			}
		}
		catch(SQLException e) {
			
			System.out.println("\nThe requested dentist can not be returned "+e);
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
		
		return dentist;
	}

	@Override
	public ArrayList<Appointment> getAllAppFromDentist(Integer dentistId){
		// TODO Auto-generated method stub
		
		ArrayList <Appointment> appFromDentist= new  ArrayList<Appointment>();
		JDBCAppointmentManager app= new JDBCAppointmentManager(manager);
		try {
			String sql = "SELECT app_id FROM dentist LEFT JOIN appointment ON doc_id=dentist_id WHERE doc_id= ?";
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			prep.setInt(1, dentistId);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				Integer appId= rs.getInt("app_id");
				Appointment appointment=app.viewAppointment(appId);
			    appFromDentist.add(appointment);
				
			}
			if(appFromDentist.size()==0) {
				
				throw new IdNotFoundException("\nThe specified id does not correspond to any of the ids "
						+ "of the dentists");

			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return appFromDentist;
	}

	@Override
	public Dentists getDentistByEmail(String dentistEmail) throws Exception {
		// TODO Auto-generated method stub
Dentists dentist= null;
		
		String sql= "SELECT * FROM dentist WHERE doc_email= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1,dentistEmail);
				
		ResultSet rs= prep.executeQuery();
		
		while(rs.next()) {
			
			Integer id= rs.getInt("doc_id");
			String name= rs.getString("name");
			Integer bankAccount= rs.getInt("bank_account");
			String email= rs.getString("doc_email");
			String mobile= rs.getString("doc_mobile");
			
			dentist= new Dentists(id, name, bankAccount,mobile, email);
			
		}

		rs.close();
		prep.close();
		
		return dentist;
	}
	
	
}
