package Interfaces;


import java.sql.Date;
import java.util.ArrayList;

import POJO.Order_supplies;

public interface Order_suppliesManager {
	
	public void placeOrder(Order_supplies order) throws Exception;
	public Order_supplies getOrder(Integer orderSupp_Id) throws Exception;
	public void deleteOrder (Integer orderSupp_Id) throws Exception;
	public ArrayList<Order_supplies> getAllOrdersFromDentist(Integer dentistId)throws Exception;
	public ArrayList<Order_supplies> getAllOrdersFromSupplier(Integer supplierId)throws Exception;
	public ArrayList<Order_supplies> getAllOrdersOrder()throws Exception;
	public void updateOrderDate(Integer orderId, Date date) throws Exception;


}
