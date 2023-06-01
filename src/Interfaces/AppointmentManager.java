package Interfaces;

import java.sql.SQLException;

import java.util.ArrayList;

import POJO.Appointment;

public interface AppointmentManager {

	public Integer createAppointment(Appointment app) throws SQLException;

	public void deleteAppointment(Integer appointmentId);

	public void updateAppointment(Appointment app);

	public Appointment viewAppointment(Integer appointmentId);

	public ArrayList<Appointment> getListAllAppointments();

}
