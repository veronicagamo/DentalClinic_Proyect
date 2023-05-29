package Interfaces;


import java.sql.Date;
import java.util.ArrayList;
import POJO.Order_supplies;

public interface Order_suppliesManager {
	
	public void placeOrder(Order_supplies order) throws Exception;
	public Order_supplies getOrder(Integer orderSupp_Id);
	public void deleteOrder (Integer orderSupp_Id);
	public ArrayList<Order_supplies> getAllOrdersFromDentist(Integer dentistId);
	public ArrayList<Order_supplies> getAllOrdersFromSupplier(Integer supplierId);
	public ArrayList<Order_supplies> getAllOrdersOrder();
	public void updateOrderDate(Integer orderId, Date date);


}
