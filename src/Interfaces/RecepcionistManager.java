package Interfaces;

public interface RecepcionistManager {

	public void addClient(Client c) throws Exception;
	public void deleteClient(int clientId) throws Exception;
	public void updateClient(Client c) throws Exception;
	public List<Client> listAllClients() throws Exception;

	
}
