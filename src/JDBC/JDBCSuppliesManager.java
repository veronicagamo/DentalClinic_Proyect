package JDBC;

import Interfaces.SuppliesManager;
import POJO.Supply;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exceptions.IdNotFoundException;


public class JDBCSuppliesManager implements SuppliesManager{

	private JDBCManager manager;
	
	public JDBCSuppliesManager(JDBCManager m){
		
		this.manager = m;
	}
	

	@Override
	public void addSupply (Supply s){
		
		try {
			
			String sql= "INSERT INTO supplies (name, amount) "
				+ " VALUES(?,?)";
		
			PreparedStatement prep= this.manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, s.getItem_name());
			prep.setInt(2, s.getItem_amount());
			
			prep.executeUpdate();
		}
		catch(SQLException e) {
			
			System.out.println("The supply has not been added successfully "+e);

		}	
		
	}
	

	@Override
	public void deleteSupply(Integer id) {
		// TODO Auto-generated method stub
		
		try {
			
			String sql= "DELETE FROM supplies "
				+ " WHERE supplies_id= ?";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setInt(1, id);
	
			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException("The specified id does not correspond to any of the ids"
						+ "of the supplies");
			}
		}
		catch(SQLException e) {
			
			System.out.println("The requested supply has not been deleted successfully "+e);
			e.printStackTrace();
		}
		catch(IdNotFoundException e) {
			
			System.out.println(e);
	
		}
		
	}


	@Override
	public ArrayList<Supply> listAllSuppliesByAmount(){
		// TODO Auto-generated method stub
		ArrayList <Supply> supplies= new ArrayList<Supply>();

		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			
			stmt= this.manager.getConnection().createStatement();	
		
			String sql="SELECT * FROM supplies ORDER BY amount";
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				
				int id = rs.getInt("supplies_id");
				String name = rs.getString("name");
				int amount = rs.getInt("amount");
				
				Supply supply = new Supply(id, name, amount);
				
				supplies.add(supply);
				rs.close();
				stmt.close();
			}
		}catch(SQLException e) {
			
			System.out.println("The supplies can not be returned "+e);
			e.printStackTrace();
		}

		return supplies;
		
	}


	@Override
	public Supply viewSupply(Integer supId) throws SQLException{
		// TODO Auto-generated method stub
		
		Supply s=null;
		ResultSet rs=null;
		PreparedStatement prep=null;
		
			
			String sql= "SELECT * FROM supplies WHERE supplies_id= ?";
		
			prep= manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, supId);
					
			rs= prep.executeQuery();
		
		
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
	public void increaseAmount(Integer a,Integer itemId) {
		// TODO Auto-generated method stub
		
		try {
			
			String sql= "UPDATE supplies "
				+ "SET amount= amount + ? WHERE supplies_id= ?";
	        PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, a);
			prep.setInt(2,itemId);
	
	
			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException("The specified id does not correspond to any of the ids"
						+ "of the supplies");
			}
		}
		catch(SQLException e) {
			
			System.out.println("The amount of supplies can not increase "+e);
			e.printStackTrace();
		}
		catch(IdNotFoundException e) {
			
			System.out.println(e);
	
		}
        	
	}


	@Override
	public void decreaseAmount(Integer a, Integer itemId) {
		// TODO Auto-generated method stub
		
		try {
			
			String sql= "UPDATE supplies "
					+ "SET amount= amount - ? WHERE supplies_id= ?";
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, a);
			prep.setInt(2,itemId);


			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException("The specified id does not correspond to any of the ids"
						+ "of the supplies");
			}
		}
		catch(SQLException e) {
			
			System.out.println("The amount of supplies can not decrease "+e);
			e.printStackTrace();
		}
		catch(IdNotFoundException e) {
			
			System.out.println(e);
	
		}
		  
	}
	
}
