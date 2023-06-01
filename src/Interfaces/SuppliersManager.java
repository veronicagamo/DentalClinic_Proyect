package Interfaces;

import java.util.ArrayList;

import POJO.Supplier;

public interface SuppliersManager {

	public Supplier getSupplierById(Integer supplierId);

	public Supplier getSupplierByName(String supplierName);

	public Supplier getSupplierByEmail(String supplierEmail) throws Exception;

	public void updateSupplier(Supplier sup) throws Exception;

	public void createSupplier(Supplier s);

	public void deleteSupplier(Integer supplierId);

	public ArrayList<Supplier> getListAllSuppliers();
}
