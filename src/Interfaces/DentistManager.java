package Interfaces;

public interface DentistManager {

	public void addDentist(Dentist d);
	public void deleteDentist(int dentistId);
	public void updateDentist(int dentistId);
	public List<Dentist> listAllDentists();
	public Dentist getDentistByName(String dName);
	
}
