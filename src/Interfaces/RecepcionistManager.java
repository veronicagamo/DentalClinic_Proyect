package Interfaces;

import java.util.ArrayList;

import POJO.Receptionist;

public interface RecepcionistManager {

	public void addReceptionist(Receptionist c);

	public void deleteReceptionist(Integer receptionistId);

	public void updateReceptionist(Receptionist c);

	public ArrayList<Receptionist> listAllReceptionists();

	public Receptionist geRecepByName(String recepName);

	public Receptionist getRecepByEmail(String recepEmail) throws Exception;

	public Receptionist getreceptionistById(Integer receptionistId);

}
