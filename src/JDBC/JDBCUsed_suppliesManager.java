package JDBC;


import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Interfaces.Used_suppliesManager;
import POJO.Appointment;
import POJO.Used_supplies;

public class JDBCUsed_suppliesManager implements Used_suppliesManager {



private JDBCManager manager;
	
	public JDBCUsed_suppliesManager(JDBCManager m) {
		
		this.manager=m;
	}
	@Override
	public ArrayList<Used_supplies> listAllUsed() throws Exception {
		// TODO Auto-generated method stub
ArrayList <Used_supplies> supplies= new ArrayList<Used_supplies>();
		
		Statement stmt= this.manager.getConnection().createStatement();	
		
		String sql="SELECT * FROM used_supplies";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()){
			
			Integer id= rs.getInt("id");
			Integer appId= rs.getInt("appointment_id");
			Integer supId= rs.getInt("supply_id");
			Integer amount= rs.getInt("amount");
			Used_supplies used= new Used_supplies(supId,appId,id, amount);
			
			supplies.add(used);
		}
		
		rs.close();
		stmt.close();

		return supplies;
		
	}

	@Override
	public void createUsed(Integer appId, Integer item_id, Integer amount){
		// TODO Auto-generated method stub
		
		try {
			
			String sql= "INSERT INTO used_supplies (supply_id,appointment_id,amount) VALUES (?,?,?)";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			prep.setInt(1,item_id);
			prep.setInt(2,appId);		
			prep.setInt(3, amount);
			prep.executeUpdate();
			
		}catch(SQLException e) {
			
			System.out.println("The used supply can not be created "+e);
			e.printStackTrace();

		}

	}

	@Override

	public ArrayList<Used_supplies> listAllUsedByAppointment(Appointment a){
		// TODO Auto-generated method stub
       ArrayList <Used_supplies> all= new  ArrayList<Used_supplies>();
       
		try {
			String sql = "SELECT * FROM used_supplies WHERE appointment_id= ?";
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			prep.setInt(1, a.getApp_id());
			ResultSet rs = prep.executeQuery();

			while(rs.next())
			{
				Integer id= rs.getInt("id");
				Integer supId= rs.getInt("supply_id");
				Integer amount= rs.getInt("amount");
				Used_supplies used= new Used_supplies(supId,a.getApp_id(),id, amount);
				all.add(used);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return all;
	}
	@Override
	public void updateUsedsupplies(Used_supplies used) throws Exception {
		// TODO Auto-generated method stub
		  String sql= "UPDATE used_supplies "
					+ "SET supply_id= ?, appointment_id= ?, amount= ? WHERE id= ?";
	PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, used.getItem_id());
			prep.setInt(2,used.getApp_id());
			prep.setInt(3,used.getAmount());
			prep.setInt(4, used.getUsed_supp_id());

			prep.executeUpdate();
	}
	@Override
	public Used_supplies viewUsedsupplies(Integer id) throws Exception {
		// TODO Auto-generated method stub
			Used_supplies used= null;
		
		String sql="SELECT * FROM used_supplies WHERE id= ?";
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		while (rs.next()){

			Integer appId= rs.getInt("appointment_id");
			Integer supId= rs.getInt("supply_id");
			Integer amount= rs.getInt("amount");
		 used= new Used_supplies(supId,appId,id, amount);
			

		}
		rs.close();
		prep.close();
		return used;

	}
	
	

}
