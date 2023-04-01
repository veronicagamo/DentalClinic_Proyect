package POJO;

import java.io.Serializable;

public class Dentists implements Serializable{


	private static final long serialVersionUID = -4597260876619998054L;
	
	private Integer doc_id;
	private String name;
	private String bank_account;
	private Integer doc_mobile;
	private String doc_email;
	private List<Appointment> appointments;
	
	
	
}
