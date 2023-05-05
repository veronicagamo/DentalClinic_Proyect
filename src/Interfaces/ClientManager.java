package Interfaces;

import java.util.ArrayList;


import POJO.Appointment;
import POJO.Client;
import POJO.Dentists;


public interface ClientManager {

	public void createClient(Client client) throws Exception;
	public void deleteClient(Integer pat_id) throws Exception;
	public void updateClient(Client client) throws Exception;
	public Client getClientById(Integer clientId) throws Exception;
	public Client getClientByName(String clientName) throws Exception;
	public Client getClientByEmail(String clientEmail) throws Exception;
	public ArrayList<Client> getListAllClients() throws Exception;
	public ArrayList<Appointment> getAllAppFromClient(Integer clientId)throws Exception;
}
