package JDBC;

import Interfaces.SuppliesManager;
import POJO.Supply;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class JDBCSuppliesManager implements SuppliesManager{

	private JDBCManager manager;
	
	public JDBCSuppliesManager(JDBCManager m){
		
		this.manager = m;
	}
	

	@Override
	public void addSupply (Supply s) throws Exception{
		
		String sql= "INSERT INTO supplies (name, amount) "
				+ " VALUES(?,?)";
		
		PreparedStatement prep= this.manager.getConnection().prepareStatement(sql);
		
		prep.setString(1, s.getItem_name());
		prep.setInt(2, s.getItem_amount());
		
		prep.executeUpdate();
		
	}
	

	@Override
	public void deleteSupply(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String sql= "DELETE FROM supplies "
				+ " WHERE supplies_id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, id);

		prep.executeUpdate();
	}


	@Override
	public ArrayList<Supply> listAllSuppliesByAmount() throws Exception {
		// TODO Auto-generated method stub
		ArrayList <Supply> supplies= new ArrayList<Supply>();
		
		Statement stmt= this.manager.getConnection().createStatement();	
		
		String sql="SELECT * FROM supplies ORDER BY amount";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()){
			
			int id = rs.getInt("supplies_id");
			String name = rs.getString("name");
			int amount = rs.getInt("amount");
			
			Supply supply = new Supply(id, name, amount);
			
			supplies.add(supply);
		}
		
		rs.close();
		stmt.close();

		return supplies;
		
	}


	@Override
	public Supply viewSupply(Integer supId) throws Exception {
		// TODO Auto-generated method stub
     Supply s=null;
		
		String sql= "SELECT * FROM supplies WHERE supplies_id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1, supId);
				
		ResultSet rs= prep.executeQuery();
		
		while(rs.next()) {
			
			String name= rs.getString("name");
			Integer amount= rs.getInt("amount");

			
			s= new Supply(supId,name,amount);
			
		}

		rs.close();
		prep.close();
		
		return s;
		
	}


	@Override
	public void increaseAmount(Integer a,Integer itemId) throws Exception {
		// TODO Auto-generated method stub
        String sql= "UPDATE supplies "
				+ "SET amount= amount + ? WHERE supplies_id= ?";
PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1, a);
		prep.setInt(2,itemId);


		prep.executeUpdate();
		
	}


	@Override
	public void decreaseAmount(Integer a, Integer itemId) throws Exception {
		// TODO Auto-generated method stub
		  String sql= "UPDATE supplies "
					+ "SET amount= amount - ? WHERE supplies_id= ?";
	PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, a);
			prep.setInt(2,itemId);


			prep.executeUpdate();
	}
	
}
