package Interfaces;



import java.util.ArrayList;

import POJO.Supplier;

public interface SuppliersManager {

	public Supplier getSupplierById(Integer supplierId) throws Exception;
	public Supplier getSupplierByName(String supplierName) throws Exception;
	public Supplier getSupplierByEmail(String supplierEmail) throws Exception;
	public void updateSupplier(Supplier sup) throws Exception;
	public void createSupplier(Supplier s) throws Exception;
	public void deleteSupplier(Integer supplierId) throws Exception;
	public ArrayList<Supplier> getListAllSuppliers() throws Exception;
}
