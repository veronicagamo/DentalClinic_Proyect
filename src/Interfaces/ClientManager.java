package Interfaces;

import java.util.ArrayList;
import java.util.Date;

import POJO.Appointment;
import POJO.Client;

public interface ClientManager {

	public void createClient(Integer pat_id, String pat_name, Integer hum, String pat_address, String pat_email,
			ArrayList<Appointment> appointments) throws Exception;
	public void deleteClient(Integer pat_id) throws Exception;
	public void updateClient(Integer pat_id, String pat_name, Integer hum, String pat_address, String pat_email,
			ArrayList<Appointment> appointments) throws Exception;
	public Client getClientById(int clientId) throws Exception;
	public ArrayList<Client> getListAllClients() throws Exception;
}
