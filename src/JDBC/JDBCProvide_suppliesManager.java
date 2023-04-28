package JDBC;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;

import Interfaces.Provide_suppliesManager;
import POJO.Provide_suplies;
import POJO.Supply;

public class JDBCProvide_suppliesManager implements Provide_suppliesManager{
	
	private JDBCManager manager;
	
	public JDBCProvide_suppliesManager(JDBCManager m){
		
		this.manager=m;
	}

	@Override
	public void createProvide(Provide_suplies prov) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProvide(Integer provideId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProvideDate(Integer provideId, Date date) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Provide_suplies getProvideById(Integer provideId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Provide_suplies> getAllSuppliesFromSupplier(Integer supplierId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Provide_suplies> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
