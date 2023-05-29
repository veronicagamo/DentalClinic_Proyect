package Interfaces;

import java.util.ArrayList;


import POJO.Appointment;

public interface AppointmentManager {

	public Integer createAppointment(Appointment app);
	public void deleteAppointment(Integer appointmentId);
	public void updateAppointment(Appointment app);
	public Appointment viewAppointment (Integer appointmentId);
	public ArrayList<Appointment> getListAllAppointments();
	
	 
}
