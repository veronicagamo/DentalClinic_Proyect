package Interfaces;

public interface RecepcionistManager {

	public void addClient(Client c);
	public void deleteClient(int clientId);
	public void updateClient(int clientId);
	public List<Client> listAllClients();
	public void assignDentist(int dentistId, int clientId);

	
}
