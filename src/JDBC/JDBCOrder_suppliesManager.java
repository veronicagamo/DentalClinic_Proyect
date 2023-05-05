package JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Interfaces.Order_suppliesManager;
import POJO.Order_supplies;



public class JDBCOrder_suppliesManager implements Order_suppliesManager{
	
private JDBCManager manager;
	
	public JDBCOrder_suppliesManager(JDBCManager m) {
		
		this.manager=m;
	}

	@Override
	public void placeOrder(Order_supplies order) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO order_supplies (supply_id, dentist_id, amount) VALUES (?,?,?) ";

		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, order.getItem_id());
		prep.setInt(2, order.getDoc_id());
		prep.setInt(3, order.getAmount());
		prep.executeUpdate();

	}

	@Override
	public Order_supplies getOrder(Integer orderSupp_Id) throws Exception {
		// TODO Auto-generated method stub
		Order_supplies o = null;

		String sql = "SELECT * FROM order_supplies WHERE id= ?";

		PreparedStatement prep = manager.getConnection().prepareStatement(sql);

		prep.setInt(1, orderSupp_Id);

		ResultSet rs = prep.executeQuery();

		while (rs.next()) {

			Integer id = rs.getInt("id");
			Integer itemId = rs.getInt("supply_id");
			Integer docId = rs.getInt("dentist_id");
			Integer amount = rs.getInt("amount");

			o = new Order_supplies(id, itemId, docId, amount);

		}

		rs.close();
		prep.close();

		return o;

	}

	@Override
	public void deleteOrder(Integer orderSupp_Id) throws Exception {
		// TODO Auto-generated method stub
		String sql= "DELETE FROM order_supplies "
				+ " WHERE id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, orderSupp_Id);

		prep.executeUpdate();
	}

	@Override
	public ArrayList<Order_supplies> getAllOrdersFromDentist(Integer dentistId) throws Exception {
		// TODO Auto-generated method stub
		 ArrayList <Order_supplies> all= new  ArrayList<Order_supplies>();
	       
			try {
				String sql = "SELECT order_supplies.id, order_supplies.supply_id, order_supplies.dentist_id,  order_supplies.amount FROM order_supplies INNER JOIN dentist ON dentist_id=doc_id WHERE dentist_id= ?";
				PreparedStatement prep= manager.getConnection().prepareStatement(sql);
				prep.setInt(1, dentistId);
				ResultSet rs = prep.executeQuery();

				while(rs.next())
				{
					Integer id= rs.getInt("id");
					Integer supId= rs.getInt("supply_id");
					Integer docId= rs.getInt("dentist_id");
					Integer amount= rs.getInt("amount");
					Order_supplies o= new Order_supplies(id, supId, docId, amount);
					all.add(o);
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return all;
	}

}
