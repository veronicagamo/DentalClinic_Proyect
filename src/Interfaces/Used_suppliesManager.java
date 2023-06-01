package Interfaces;

import java.util.ArrayList;
import POJO.*;

public interface Used_suppliesManager {

	public ArrayList<Used_supplies> listAllUsed()throws Exception;

	public void updateUsedsupplies(Used_supplies used) throws Exception;

	public void createUsed(Integer appId, Integer item_id, Integer amount) throws Exception;

	public Used_supplies viewUsedsupplies(Integer id) throws Exception;

	public ArrayList<Used_supplies> listAllUsedByAppointment(Appointment a) throws Exception;
}
