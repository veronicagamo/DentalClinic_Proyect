package Interfaces;

import java.io.File;
import POJO.*;

public interface XMLManager {
    
	public void client2xml(Integer id);
	public void supplier2xml(Supplier sup);
	public Supplier xml2Supplier (File xml);
	public Client xml2Client (File xml);
	public void simpleTransform(String sourcePath, String xsltPath,String resultDir);
}
