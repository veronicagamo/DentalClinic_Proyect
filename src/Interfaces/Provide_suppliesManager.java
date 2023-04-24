package Interfaces;

import java.util.ArrayList;


import POJO.Provide_suplies;


public interface Provide_suppliesManager {

	public void createProvide(Provide_suplies prov) throws Exception;
	public void deleteProvide(Integer provideId) throws Exception;
	public Provide_suplies getProvideById(Integer provideId) throws Exception;
	public ArrayList<Provide_suplies> getAllSuppliesFromSupplier(Integer supplierId)throws Exception;
	public ArrayList<Provide_suplies> getAllSupplies()throws Exception;
}
