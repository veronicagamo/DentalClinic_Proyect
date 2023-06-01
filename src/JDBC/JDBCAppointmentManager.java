package JDBC;

import Interfaces.AppointmentManager;

import POJO.Appointment;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import Exceptions.*;

public class JDBCAppointmentManager implements AppointmentManager {

	private JDBCManager manager;

	public JDBCAppointmentManager(JDBCManager m) {

		this.manager = m;
	}

	@Override
	public Integer createAppointment(Appointment app) throws SQLException {

		Integer id = null;

		String sql = "INSERT INTO appointment (app_date, app_duration, app_room, app_price, app_treatment, dentist_id,recepcionist_id, client_id) "
				+ " VALUES(?,?,?,?,?,?,?,?)";

		PreparedStatement prep = manager.getConnection().prepareStatement(sql);

		prep.setDate(1, app.getApp_date());
		prep.setInt(2, app.getApp_duration());
		prep.setInt(3, app.getApp_room());
		prep.setDouble(4, app.getApp_price());
		prep.setString(5, app.getApp_treatment());
		prep.setInt(6, app.getDentist());
		prep.setInt(7, app.getReceptionist());
		prep.setInt(8, app.getPatient());
		prep.executeUpdate();
		prep.close();

		String sql2 = "SELECT app_id FROM appointment ORDER BY app_id DESC LIMIT 1";
		PreparedStatement prep2 = manager.getConnection().prepareStatement(sql2);
		ResultSet rs = prep2.executeQuery();
		id = rs.getInt("app_id");
		rs.close();
		prep2.close();

		return id;
	}

	@Override
	public void deleteAppointment(Integer appointmentId) {
		PreparedStatement prep = null;
		try {

			String sql = "DELETE FROM appointment " + " WHERE app_id= ?";

			prep = manager.getConnection().prepareStatement(sql);

			prep.setInt(1, appointmentId);

			if (prep.executeUpdate() == 0) {

				throw new IdNotFoundException(
						"\nThe specified id does not correspond to any of the ids " + "of the appointments");
			}

		} catch (IdNotFoundException e) {

			System.out.println(e);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {

				if (prep != null) {
					prep.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}

	@Override
	public void updateAppointment(Appointment app) {
		PreparedStatement prep = null;
		try {
			String sql = "UPDATE appointment "
					+ "SET app_date= ?, app_duration= ?, app_room= ?, app_price= ?, app_treatment= ?, dentist_id= ?, recepcionist_id= ?, client_id= ? "
					+ "WHERE app_id=?";

			prep = manager.getConnection().prepareStatement(sql);

			prep.setDate(1, (java.sql.Date) app.getApp_date());
			prep.setInt(2, app.getApp_duration());
			prep.setInt(3, app.getApp_room());
			prep.setDouble(4, app.getApp_price());
			prep.setString(5, app.getApp_treatment());
			prep.setInt(6, app.getDentist());
			prep.setInt(7, app.getReceptionist());
			prep.setInt(8, app.getPatient());
			prep.setInt(9, app.getApp_id());

			if (prep.executeUpdate() == 0) {

				throw new IdNotFoundException(
						" \nThe specified id does not correspond to any of the ids " + "of the appointments");
			}
		} catch (IdNotFoundException e) {

			System.out.println(e);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {

				if (prep != null) {
					prep.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}

	@Override
	public Appointment viewAppointment(Integer appointmentId) {

		Appointment app = null;
		ResultSet rs = null;
		PreparedStatement prep = null;
		try {

			String sql = "SELECT * FROM appointment WHERE app_id= ?";

			prep = manager.getConnection().prepareStatement(sql);

			prep.setInt(1, appointmentId);

			rs = prep.executeQuery();

			while (rs.next()) {
				Date dat = rs.getDate("app_date");
				Integer duration = rs.getInt("app_duration");
				Integer room = rs.getInt("app_room");
				Double price = rs.getDouble("app_price");
				String treatment = rs.getString("app_treatment");
				Integer den = rs.getInt("dentist_id");
				Integer rec = rs.getInt("recepcionist_id");
				Integer patient = rs.getInt("client_id");

				app = new Appointment(appointmentId, (java.sql.Date) dat, duration, room, price, treatment, den, rec,
						patient);
			}
			if (app == null) {
				throw new IdNotFoundException(
						" \nThe specified id does not correspond to any of the ids " + "of the appointments");
			}
		} catch (IdNotFoundException e) {

			System.out.println(e);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {

				if (rs != null) {
					rs.close();
				}
				if (prep != null) {
					prep.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return app;
	}

	public ArrayList<Appointment> getListAllAppointments() {

		ArrayList<Appointment> all = new ArrayList<Appointment>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM appointment";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Integer id = rs.getInt("app_id");
				Date dat = rs.getDate("app_date");
				Integer duration = rs.getInt("app_duration");
				Integer room = rs.getInt("app_room");
				Double price = rs.getDouble("app_price");
				String treatment = rs.getString("app_treatment");
				Integer den = rs.getInt("dentist_id");
				Integer rec = rs.getInt("recepcionist_id");
				Integer patient = rs.getInt("client_id");

				Appointment app = new Appointment(id, (java.sql.Date) dat, duration, room, price, treatment, den, rec,
						patient);
				all.add(app);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {

			try {

				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return all;
	}

}
