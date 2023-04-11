package Interfaces;

import POJO.Client;

public interface ClientManager {

	public Client getClientById(int clientId) throws Exception;
}
