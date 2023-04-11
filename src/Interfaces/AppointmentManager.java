package Interfaces;

import java.util.Date;

public interface AppointmentManager {

	public void createAppointment(Date date, int room, int dentistId, int clientId, int supplyId) throws Exception;
	public void deleteAppointment(int appointmentId) throws Exception;
	public void updateAppointment(int appointmentId, Date date, int room, int dentistId, int clientId, int supplyId) throws Exception;

}
