package JDBC;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exceptions.IdNotFoundException;
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
		String sql = "INSERT INTO order_supplies (supply_id, dentist_id, amount,supplier_id,date) VALUES (?,?,?,?,?) ";

		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, order.getItem_id());
		prep.setInt(2, order.getDoc_id());
		prep.setInt(3, order.getAmount());
		prep.setInt(4, order.getSupplierId());
		prep.setDate(5, (java.sql.Date) order.getDate());
		prep.executeUpdate();
        prep.close();
	}

	@Override
	public Order_supplies getOrder(Integer orderSupp_Id) {
		// TODO Auto-generated method stub
		Order_supplies o = null;
		PreparedStatement prep=null;
		ResultSet rs=null;
try {
		String sql = "SELECT * FROM order_supplies WHERE id= ?";

		prep = manager.getConnection().prepareStatement(sql);

		prep.setInt(1, orderSupp_Id);

		rs = prep.executeQuery();

		while (rs.next()) {

			Integer id = rs.getInt("id");
			Integer itemId = rs.getInt("supply_id");
			Integer docId = rs.getInt("dentist_id");
			Integer amount = rs.getInt("amount");
			Integer supId = rs.getInt("supplier_id");
			Date date= rs.getDate("date");

			o = new Order_supplies(id, itemId, docId, amount,date,supId);

		}
      if (o==null) {
    	  throw new IdNotFoundException(" \nThe specified id does not correspond to any of the ids");
      }}
catch(IdNotFoundException e) {
	
	System.out.println(e);
}   catch(SQLException e) {
	
	e.printStackTrace();

}
finally {
	
	try {
		
		if(prep!=null) {
			prep.close();
		}    
		if(rs!=null) {
			rs.close();
		}
	}
	catch(SQLException e) {
		
		e.printStackTrace();
	}

}


		return o;

	}

	@Override
	public void deleteOrder(Integer orderSupp_Id) {
		// TODO Auto-generated method stub
		PreparedStatement prep=null;
		try {
		String sql= "DELETE FROM order_supplies "
				+ " WHERE id= ?";
		 prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, orderSupp_Id);
		 if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException("\nThe specified id does not correspond to any of the ids");
			}
		}
      catch(SQLException e) {
			
			System.out.println(" \nThe requested order has not been deleted successfully "+e);
			e.printStackTrace();

		}
		catch(IdNotFoundException e) {
			
			System.out.println(e);
		}
		finally {
			
			try {
				
				if(prep!=null) {
					prep.close();
				}    
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}

		}
		
	}

	@Override
	public ArrayList<Order_supplies> getAllOrdersFromDentist(Integer dentistId) {
		// TODO Auto-generated method stub
		 ArrayList <Order_supplies> all= new  ArrayList<Order_supplies>();
		 PreparedStatement prep= null;
			ResultSet rs =null;
			try {
				String sql = "SELECT * FROM order_supplies INNER JOIN dentist ON dentist_id=doc_id WHERE dentist_id= ?";
				 prep= manager.getConnection().prepareStatement(sql);
				prep.setInt(1, dentistId);
				 rs = prep.executeQuery();

				while(rs.next())
				{
					Integer id= rs.getInt("id");
					Integer supId= rs.getInt("supply_id");
					Integer docId= rs.getInt("dentist_id");
					Integer amount= rs.getInt("amount");
					Integer suppId = rs.getInt("supplier_id");
					Date date= rs.getDate("date");
					Order_supplies o= new Order_supplies(id, supId, docId, amount, date,suppId);
					all.add(o);
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				
				try {
					
					if(prep!=null) {
						prep.close();
					}    
					if(rs!=null) {
						rs.close();
					}
				}
				catch(SQLException e) {
					
					e.printStackTrace();
				}

			}
			return all;
	}

	@Override
	public ArrayList<Order_supplies> getAllOrdersFromSupplier(Integer supplierId)  {
		// TODO Auto-generated method stub
		ArrayList <Order_supplies> prov= new  ArrayList<Order_supplies>();
		PreparedStatement prep= null;
		ResultSet rs =null;
		try {
			String sql = "SELECT * FROM order_supplies WHERE supplier_id= ?";
			 prep= manager.getConnection().prepareStatement(sql);
			prep.setInt(1, supplierId);
			 rs = prep.executeQuery();
			
			while(rs.next())
			{
				Integer id= rs.getInt("id");
				Integer supId= rs.getInt("supply_id");
				Integer docId= rs.getInt("dentist_id");
				Integer amount= rs.getInt("amount");
				Date date= rs.getDate("date");
				Order_supplies o= new Order_supplies(id, supId, docId, amount, date,supplierId);
				prov.add(o);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				
				if(prep!=null) {
					prep.close();
				}    
				if(rs!=null) {
					rs.close();
				}
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}

		}
		return prov;
	}

	@Override
	public void updateOrderDate(Integer orderId, Date date){
		// TODO Auto-generated method stub
		PreparedStatement prep=null;
		try {
		  String sql= "UPDATE order_supplies "
					+ "SET date= ? WHERE id= ?";
	 prep= manager.getConnection().prepareStatement(sql);
			
			prep.setDate(1, date);
			prep.setInt(2,orderId);
          if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException(" \nThe specified id does not correspond to any of the ids");
			}
         }
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				
				if(prep!=null) {
					prep.close();
				}    
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}

		}
		
	}

	@Override
	public ArrayList<Order_supplies> getAllOrdersOrder() {
		// TODO Auto-generated method stub
		ArrayList <Order_supplies> prov= new  ArrayList<Order_supplies>();
		PreparedStatement prep=null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM order_supplies ORDER BY date";
			prep= manager.getConnection().prepareStatement(sql);
			 rs = prep.executeQuery();
			
			while(rs.next())
			{
				Integer id= rs.getInt("id");
				Integer supId= rs.getInt("supply_id");
				Integer docId= rs.getInt("dentist_id");
				Integer amount= rs.getInt("amount");
				Date date= rs.getDate("date");
				Integer supplierId= rs.getInt("supplier_id");
				Order_supplies o= new Order_supplies(id, supId, docId, amount, (java.sql.Date) date,supplierId);
				prov.add(o);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				
				if(prep!=null) {
					prep.close();
				}    
				if(rs!=null) {
					rs.close();
				}
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}

		}
		return prov;
	}

}
