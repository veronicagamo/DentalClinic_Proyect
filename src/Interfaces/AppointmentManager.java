package Interfaces;

import java.util.ArrayList;

import POJO.Appointment;
import POJO.Used_supplies;

public interface AppointmentManager {

	public Integer createAppointment(Appointment app) throws Exception;
	public void deleteAppointment(Integer appointmentId) throws Exception;
	public void updateAppointment(Appointment app) throws Exception;
	public Appointment viewAppointment (Integer appointmentId) throws Exception;
	public ArrayList<Appointment> getListAllAppointments() throws Exception;
	
	 
}
