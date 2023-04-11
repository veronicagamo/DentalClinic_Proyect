package JDBC;

import Interfaces.AppointmentManager;
import POJO.Appointment;

import java.sql.PreparedStatement;
import java.util.Date;

public class JDBCAppointmentManager implements AppointmentManager{
	
	private JDBCManager manager;
	
	public JDBCAppointmentManager(JDBCManager m) {
		
		this.manager=m;
	}

	@Override
	public void createAppointment(Date date, int room, int dentistId, int clientId, int supplyId) throws Exception{
		
		String sql= "INSERT INTO appointment (date, room, dentistId, clientId, supplyId) "
				+ " VALUES(?,?,?,?,?)";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setDate(1, date);
			prep.setInt(2, room);
			prep.setInt(3, dentistId);
			prep.setInt(4, clientId);
			prep.setInt(5, supplyId);

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
		
		prep.setDate(1, date);
		prep.setInt(2, room);
		prep.setInt(3, dentistId);
		prep.setInt(4, clientId);
		prep.setInt(5, supplyId);
		prep.setInt(6, appointmentId);

		prep.executeUpdate();
	}

}
