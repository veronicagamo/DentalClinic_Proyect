package Interfaces;

import java.util.ArrayList;


import POJO.*;

public interface DentistManager {

	public void addDentist(Dentists d);
	public void deleteDentist(Integer dentistId);
	public void updateDentist(Dentists d);
	public ArrayList<Dentists> listAllDentists();
	public Dentists getDentistById(Integer dentistId);
	public Dentists getDentistByName(String dentistName);
	public Dentists getDentistByEmail(String dentistEmail) throws Exception;
	public ArrayList<Appointment> getAllAppFromDentist(Integer dentistId);
	
}
