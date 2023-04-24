package Interfaces;

import java.util.ArrayList;

import POJO.Order_supplies;

public interface Order_suppliesManager {
	
	public Order_supplies placeOrder(Order_supplies order) throws Exception;
	public Order_supplies getOrder(Integer orderSupp_Id) throws Exception;
	public void deleteOrder (Integer orderSupp_Id) throws Exception;
	public ArrayList<Order_supplies> getAllOrdersFromDentist(Integer dentistId)throws Exception;
	

}
