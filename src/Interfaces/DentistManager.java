package Interfaces;

import java.util.ArrayList;

import POJO.Dentists;

public interface DentistManager {

	public void addDentist(Dentists d) throws Exception;
	public void deleteDentist(int dentistId) throws Exception;
	public void updateDentist(Dentists d) throws Exception;
	public ArrayList<Dentists> listAllDentists() throws Exception;
	public Dentists getDentistByName(String dName) throws Exception;
	
}
