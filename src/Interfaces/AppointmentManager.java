package Interfaces;

public interface AppointmentManager {

	public void createAppointment(DateTime date, int room, int dentistId, int clientId, int supplyId);
	public void deleteAppointment(DateTime date, int room, int dentistId, int clientId, int supplyId);
	public void updateAppointment(DateTime date, int room, int dentistId, int clientId, int supplyId);

}
