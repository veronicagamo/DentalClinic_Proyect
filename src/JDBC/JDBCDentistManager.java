package JDBC;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Interfaces.DentistManager;
import POJO.Appointment;
import POJO.Dentists;

public class JDBCDentistManager implements DentistManager{

	private JDBCManager manager;
	
	public JDBCDentistManager(JDBCManager m) {
		
		this.manager=m;
	}

	@Override
	public void addDentist(Dentists d) throws Exception {
		
		String sql= "INSERT INTO dentist (name, bank_account, doc_email, doc_mobile) VALUES(?,?,?,?)";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setString(1, d.getName());
			prep.setInt(2, d.getBank_account());
			prep.setString(3, d.getDoc_email()); 
			prep.setString(4, d.getDoc_mobile());
			prep.executeUpdate();
	}

	@Override
	public void deleteDentist(Integer dentistId) throws Exception {

		String sql= "DELETE FROM dentist "
				+ " WHERE doc_id= ?";
		
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, dentistId);

		prep.executeUpdate();
	}

	@Override
	public void updateDentist(Dentists d) throws Exception {
		
		String sql= "UPDATE dentist SET name= ?, bank_account= ?, doc_email= ?, doc_mobile= ? WHERE doc_id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1, d.getName());
		prep.setInt(2, d.getBank_account());
		prep.setString(3, d.getDoc_email());
		prep.setString(4, d.getDoc_mobile());
		prep.setInt(5, d.getDoc_id());
		prep.executeUpdate();

		
	}

	@Override
	public ArrayList<Dentists> listAllDentists() throws Exception {

		ArrayList <Dentists> dentists= new ArrayList<>();
		
		Statement stmt= manager.getConnection().createStatement();	
		
		String sql= "SELECT * FROM dentist";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()){
			
			Integer id = rs.getInt("doc_id");
			String name = rs.getString("name");
			Integer bankAccount = rs.getInt("bank_account");
			String email = rs.getString("doc_email");
			String mobile = rs.getString("doc_mobile");
			
			Dentists dentist = new Dentists (id, name, bankAccount, mobile, email);
			
			dentists.add(dentist);
		}
		
		rs.close();
		stmt.close();

		return dentists;
	}

	@Override
	public Dentists getDentistById(Integer id) throws Exception {


		Dentists dentist= null;
		
		String sql= "SELECT * FROM dentist WHERE doc_id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1, id);
				
		ResultSet rs= prep.executeQuery();
		
		while(rs.next()) {
			
			String name= rs.getString("name");
			Integer bankAccount= rs.getInt("bank_account");
			String mobile= rs.getString("doc_mobile");
			String email= rs.getString("doc_email");
			
			dentist= new Dentists(id, name, bankAccount,mobile, email);
			
		}

		rs.close();
		prep.close();
		
		return dentist;
	}

	@Override
	public Dentists getDentistByName(String dentistName) throws Exception {
	Dentists dentist= null;
		
		String sql= "SELECT * FROM dentist WHERE name=?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1,dentistName);
				
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

	@Override
	public ArrayList<Appointment> getAllAppFromDentist(Integer dentistId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList <Appointment> appFromDentist= new  ArrayList<Appointment>();
		JDBCAppointmentManager app= new JDBCAppointmentManager(manager);
		try {
			String sql = "SELECT app_id FROM dentist LEFT JOIN appointment ON doc_id=dentist_id WHERE doc_id= ? GROUP BY doc_id";
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			prep.setInt(1, dentistId);
			ResultSet rs = prep.executeQuery(sql);
			
			while(rs.next())
			{
				Integer appId= rs.getInt("app_id");
				Appointment appointment=app.viewAppointment(appId);
			    appFromDentist.add(appointment);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return appFromDentist;
	}
	
	
}
