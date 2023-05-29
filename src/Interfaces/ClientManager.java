package Interfaces;

import java.util.ArrayList;



import POJO.*;

public interface ClientManager {

	public void createClient(Client client);
	public void deleteClient(Integer pat_id);
	public void updateClient(Client client);
	public Client getClientById(Integer clientId);
	public Client getClientByName(String clientName);
	public Client getClientByEmail(String clientEmail)throws Exception;
	public ArrayList<Client> getListAllClients();
	public ArrayList<Appointment> getAllAppFromClient(Integer clientId);
}
