package Interfaces;

import java.util.ArrayList;
import java.util.Date;

import POJO.Appointment;

public interface AppointmentManager {

	public void createAppointment(Appointment app) throws Exception;
	public void deleteAppointment(int appointmentId) throws Exception;
	public void updateAppointment(int appointmentId, Date date, int room, int dentistId, int clientId, int supplyId) throws Exception;
	public Appointment viewAppointment (int appointmentId) throws Exception;
	public ArrayList<Appointment> getListAllAppointments() throws Exception;
}
