package JDBC;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import Interfaces.Provide_suppliesManager;
import POJO.Supply;

public class JDBCProvide_suppliesManager implements Provide_suppliesManager{
	
	private JDBCManager manager;
	
	public JDBCProvide_suppliesManager(JDBCManager m){
		
		this.manager=m;
	}

	@Override
	public Supply getSupplyById(int supplyId) throws Exception {

		Supply supplies= null;
		
		String sql= "SELECT * FROM supplies"
				+ "WHERE id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1, supplyId);
				
		ResultSet rs= prep.executeQuery();
		
		if(rs.next()) {
			
			int id=rs.getInt("id");
			String name= rs.getString("name");
			int amount= rs.getInt("amount");
			
			supplies= new Supply(id, name, amount);
			
		}

		rs.close();
		prep.close();
		
		return supplies;

	}

}
