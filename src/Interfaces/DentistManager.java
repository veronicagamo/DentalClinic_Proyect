package Interfaces;

import java.util.ArrayList;

import POJO.Appointment;
import POJO.Dentists;

public interface DentistManager {

	public void addDentist(Dentists d) throws Exception;
	public void deleteDentist(Integer dentistId) throws Exception;
	public void updateDentist(Dentists d) throws Exception;
	public ArrayList<Dentists> listAllDentists() throws Exception;
	public Dentists getDentistById(Integer dentistId) throws Exception;
	public Dentists getDentistByName(String dentistName) throws Exception;
	public ArrayList<Appointment> getAllAppFromDentist(Integer dentistId)throws Exception;
	
}
