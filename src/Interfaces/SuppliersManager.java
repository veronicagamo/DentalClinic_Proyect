package Interfaces;



import java.util.ArrayList;

import POJO.Supplier;

public interface SuppliersManager {

	public Supplier getSupplierById(Integer supplierId) throws Exception;
	public void updateSupplier(Integer supplierId) throws Exception;
	public void createSupplier(Supplier s) throws Exception;
	public void deleteSupplier(Integer supplierId) throws Exception;
	public ArrayList<Supplier> getListAllSuppliers() throws Exception;
}
