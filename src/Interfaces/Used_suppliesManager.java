package Interfaces;

import java.util.ArrayList;


import POJO.Used_supplies;

public interface Used_suppliesManager {
    
	public ArrayList<Used_supplies> listAllUsedByAppointment() throws Exception;
	public ArrayList<Used_supplies> listAllUsed() throws Exception;
	public void createUsed(Used_supplies used) throws Exception;
	
}
