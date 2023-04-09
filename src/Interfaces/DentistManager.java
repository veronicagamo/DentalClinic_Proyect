package Interfaces;

public interface DentistManager {

	public void addDentist(Dentist d) throws Exception;
	public void deleteDentist(int dentistId) throws Exception;
	public void updateDentist(int dentistId) throws Exception;
	public List<Dentist> listAllDentists() throws Exception;
	public Dentist getDentistByName(String dName) throws Exception;
	
}
