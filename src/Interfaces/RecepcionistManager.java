package Interfaces;

import java.util.ArrayList;

import POJO.Client;

public interface RecepcionistManager {

	public void addClient(Client c) throws Exception;
	public void deleteClient(int clientId) throws Exception;
	public void updateClient(Client c) throws Exception;
	public ArrayList<Client> listAllClients() throws Exception;

	
}
