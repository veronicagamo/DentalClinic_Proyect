package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exceptions.IdNotFoundException;
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
		return null;
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
			String sql = "SELECT used_supplies.id, used_supplies.supply_id, used_supplies.appointment_id,  used_supplies.amount FROM used_supplies INNER JOIN appointment ON appointment_id=app_id  WHERE appointment_id= ?";
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			prep.setInt(1, a.getApp_id());
			ResultSet rs = prep.executeQuery(sql);

			while(rs.next())
			{
				Integer id= rs.getInt("id");
				Integer appId= rs.getInt("appointment_id");
				Integer supId= rs.getInt("supply_id");
				Integer amount= rs.getInt("amount");
				Used_supplies used= new Used_supplies(supId,appId,id, amount);
				all.add(used);
				
			}
			if(all.size()==0) {
				
				throw new IdNotFoundException("The specified id does not correspond to any of the ids"
						+ "of the appointments");

			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return all;
	}
	
	

}
