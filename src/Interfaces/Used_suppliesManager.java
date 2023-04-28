package Interfaces;

import java.util.ArrayList;

import POJO.Appointment;
import POJO.Used_supplies;

public interface Used_suppliesManager {
    
	public ArrayList<Used_supplies> listAllUsed() throws Exception;
	public void createUsed(Integer appId, Integer item_id, Integer amount) throws Exception;
	public ArrayList<Used_supplies> listAllUsedByAppointment(Appointment a) throws Exception;
}
