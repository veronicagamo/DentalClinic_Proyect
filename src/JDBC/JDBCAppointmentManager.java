package JDBC;

import Interfaces.AppointmentManager;

import POJO.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class JDBCAppointmentManager implements AppointmentManager{
	
	private Connection manager;
	
	public JDBCAppointmentManager(Connection manager) {
		
		this.manager=manager;
	}

	@Override
	public void createAppointment(Appointment app) throws Exception{
		
		String sql= "INSERT INTO appointment (app_date, app_duration, app_room, app_price, app_treatment, dentist_id,receptionist_id, patient_id) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
		
			PreparedStatement prep= manager.prepareStatement(sql);
		
			prep.setDate(1, (java.sql.Date) app.getApp_date());
			prep.setInt(2, app.getApp_duration());
			prep.setInt(3, app.getApp_room());
			prep.setDouble(4, app.getApp_price());
			prep.setString(5, app.getApp_treatment());
			prep.setInt(6, app.getDentist());
			prep.setInt(7, app.getReceptionist());
			prep.setInt(8, app.getPatient());
			prep.executeUpdate();
		
	}

	@Override
	public void deleteAppointment(Integer appointmentId) throws Exception{
		
		String sql= "DELETE FROM appointment "
				+ " WHERE app_id= ?";
		
		PreparedStatement prep= manager.prepareStatement(sql);
	
		prep.setInt(1, appointmentId);

		prep.executeUpdate();
	}

	@Override
	public void updateAppointment(Appointment app) throws Exception{
		
		String sql="UPDATE appointment "
				+ "SET app_date=?, app_duration=?, app_room=?, app_price=?, app_treatment=?, dentist_id=?, receptionist_id=?, patient_id=? "
				;
		
		PreparedStatement prep= manager.prepareStatement(sql);
		
		prep.setDate(1, (java.sql.Date) app.getApp_date());
		prep.setInt(2, app.getApp_duration());
		prep.setInt(3, app.getApp_room());
		prep.setDouble(4, app.getApp_price());
		prep.setString(5, app.getApp_treatment());
		prep.setInt(6, app.getDentist());
		prep.setInt(7, app.getReceptionist());
		prep.setInt(8, app.getPatient());

		prep.executeUpdate();
	}

	@Override
	public Appointment viewAppointment (Integer appointmentId) throws Exception {
		
		Appointment app = null;
		
		String sql="SELECT * "
				+ "FROM appointment WHERE app_id="+appointmentId;
		
	   Statement prep= manager.createStatement();
	   ResultSet rs = prep.executeQuery(sql);
	   
	   Date dat = rs.getDate("app_date");
	   Integer duration= rs.getInt("app_duration");
	   Integer room = rs.getInt("app_room");
	   Double price= rs.getDouble("app_price");
	   String treatment=  rs.getString("app_treatment");
	   Integer den =  rs.getInt("dentist_id");
	   Integer rec =rs.getInt("receptionist_id");
	   Integer patient =rs.getInt("patient_id");
	   
	   app = new Appointment(appointmentId, (java.sql.Date) dat,duration, room, price, treatment, den, rec, patient);
		
	   
	   return app;
	
		
	}

	public ArrayList<Appointment> getListAllAppointments() throws Exception {
		
		ArrayList<Appointment> all = new ArrayList<Appointment>();
		try {
			Statement stmt = manager.createStatement();
			String sql = "SELECT * FROM appointment";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				   Integer id= rs.getInt("app_id");  
				   Date dat = rs.getDate("app_date");
				   Integer duration= rs.getInt("app_duration");
				   Integer room = rs.getInt("app_room");
				   Double price= rs.getDouble("app_price");
				   String treatment=  rs.getString("app_treatment");
				   Integer den =  rs.getInt("dentist_id");
				   Integer rec =rs.getInt("receptionist_id");
				   Integer patient =rs.getInt("patient_id");
				   
				   
				  Appointment app = new Appointment(id, (java.sql.Date) dat,duration, room, price, treatment, den, rec, patient);
				  all.add(app);
			}
			
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return all;
	}
	


}
