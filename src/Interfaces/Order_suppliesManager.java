package Interfaces;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import POJO.Order_supplies;

public interface Order_suppliesManager {
	
	public Order_supplies placeOrder(Order_supplies order) throws Exception;
	public Order_supplies getOrder(Integer orderSupp_Id) throws Exception;
	public void deleteOrder (Integer orderSupp_Id) throws Exception;
	public ArrayList<Order_supplies> getAllOrdersFromDentist(Integer dentistId)throws Exception;
	
//	@Override
//	public void orderSupplies(int supplyId, int dentistId) throws Exception {
//		
//		String sql="INSERT INTO order_supplies (supply_id, dentist_id)"
//				+ "VALUES (?,?) ";
//		
//		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
//		
//		prep.setInt(1, supplyId);
//		prep.setInt(2, dentistId);
//		
//		prep.executeUpdate();
//
//	}


}
