package Interfaces;

import java.util.ArrayList;

import POJO.Receptionist;

public interface RecepcionistManager {

	public void addReceptionist(Receptionist c) throws Exception;
	public void deleteReceptionist(Integer receptionistId) throws Exception;
	public void updateReceptionist(Receptionist c) throws Exception;
	public ArrayList<Receptionist> listAllReceptionists() throws Exception;
	public Receptionist geRecepByName(String recepName) throws Exception;
	public Receptionist getRecepByEmail(String recepEmail) throws Exception;
	public Receptionist getreceptionistById(Integer receptionistId) throws Exception;

	
}
