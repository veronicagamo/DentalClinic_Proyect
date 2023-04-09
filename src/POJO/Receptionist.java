package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Receptionist implements Serializable {

	private static final long serialVersionUID = 7681173821305767706L;

	private Integer rep_id;
	private Integer rep_name;
	private String bank_account;
	private String rep_email;
	private Integer rep_mobile;
	private ArrayList<Appointment> appointments;
	
	public Receptionist(Integer rep_id, Integer rep_name, String bank_account, String rep_email, Integer rep_mobile,
			ArrayList<Appointment> appointments) {
		super();
		this.rep_id = rep_id;
		this.rep_name = rep_name;
		this.bank_account = bank_account;
		this.rep_email = rep_email;
		this.rep_mobile = rep_mobile;
		this.appointments = appointments;
	}

	public Receptionist() {
		super();
		this.appointments= new ArrayList<Appointment>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(rep_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receptionist other = (Receptionist) obj;
		return Objects.equals(rep_id, other.rep_id);
	}

	public Integer getRep_id() {
		return rep_id;
	}

	public void setRep_id(Integer rep_id) {
		this.rep_id = rep_id;
	}

	public Integer getRep_name() {
		return rep_name;
	}

	public void setRep_name(Integer rep_name) {
		this.rep_name = rep_name;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getRep_email() {
		return rep_email;
	}

	public void setRep_email(String rep_email) {
		this.rep_email = rep_email;
	}

	public Integer getRep_mobile() {
		return rep_mobile;
	}

	public void setRep_mobile(Integer rep_mobile) {
		this.rep_mobile = rep_mobile;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}
	 
	
	
	
	

 
}
