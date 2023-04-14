package JDBC;

import Interfaces.AppointmentManager;
import POJO.Appointment;
import POJO.Client;
import POJO.Dentists;
import POJO.Receptionist;
import POJO.Supply;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class JDBCAppointmentManager implements AppointmentManager{
	
	private JDBCManager manager;
	
	public JDBCAppointmentManager(JDBCManager m) {
		
		this.manager=m;
	}

	@Override
	public void createAppointment(Appointment app) throws Exception{
		
		String sql= "INSERT INTO appointment (app_date, app_duration, app_room, app_price, app_treatment, dentist_id,receptionist_id, patient_id, used_supp) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setDate(1, (java.sql.Date) app.getApp_date());
			prep.setInt(2, app.getApp_room());
			prep.setInt(3, app.getDentist());
			prep.setInt(4, app.getPatient());
			//  prep.setArray(5, (Array) app.getUsed_supp());

			prep.executeUpdate();
		
	}

	@Override
	public void deleteAppointment(int appointmentId) throws Exception{
		
		String sql= "DELETE FROM appointment "
				+ " WHERE id= ?";
		
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, appointmentId);

		prep.executeUpdate();
	}

	@Override
	public void updateAppointment(int appointmentId, Date date, int room, int dentistId, int clientId, int supplyId) throws Exception{
		
		String sql="UPDATE appointment "
				+ "SET date= ?, room=?, dentistId=?, clientId=?, supplyId=?"
				+ "WHERE id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setDate(1, (java.sql.Date) date);
		prep.setInt(2, room);
		prep.setInt(3, dentistId);
		prep.setInt(4, clientId);
		prep.setInt(5, supplyId);
		prep.setInt(6, appointmentId);

		prep.executeUpdate();
	}

	@Override
	public Appointment viewAppointment (int appointmentId) throws Exception {
		
		Appointment app = null;
		
		String sql="SELECT * "
				+ "FROM appointment WHERE appointmentId="+appointmentId;
		
	   Statement prep= manager.getConnection().createStatement();
	   ResultSet rs = prep.executeQuery(sql);
	   
	   Date dat = rs.getDate("app_date");
	   Integer duration= rs.getInt("app_duration");
	   Integer room = rs.getInt("app_room");
	   Double price= rs.getDouble("app_price");
	   String treatment=  rs.getString("app_treatment");
	   Integer den =  rs.getInt("dentist_id");
	   Integer rec =rs.getInt("receptionist_id");
	   Integer patient =rs.getInt("patient_id");
	   
	   
	   
	   // ArrayList<Supply> used_supp= rs.get("used_supp");
	   
	   
	  // app = new Appointment(appointmentId, dat,duration, room, price, treatment, den, rec, patient, used_sup)
		
	   
	   return app;
	
		
	}

	public ArrayList<Appointment> getListAllAppointments() throws Exception {
		
		ArrayList<Appointment> all = new ArrayList<Appointment>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM appointment";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				
				   Date dat = rs.getDate("app_date");
				   Integer duration= rs.getInt("app_duration");
				   Integer room = rs.getInt("app_room");
				   Double price= rs.getDouble("app_price");
				   String treatment=  rs.getString("app_treatment");
				   Integer den =  rs.getInt("dentist_id");
				   Integer rec =rs.getInt("receptionist_id");
				   Integer patient =rs.getInt("patient_id");
				   // ArrayList<Supply> used_supp= rs.getUsed_supp("used_supp");
				   
				   
				  //Appointment app = new Appointment(appointmentId, dat,duration, room, price, treatment, den, rec, patient, used_sup)
				//all.add(app);
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
