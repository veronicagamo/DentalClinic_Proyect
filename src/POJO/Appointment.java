package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Appointment implements Serializable{

	private static final long serialVersionUID = -2549829229281834329L;
	
	
	private Integer app_id;
	private Date app_date;
	private Integer app_duration;
	private Integer app_room;
	private Double app_price;
	private String app_treatment;
	private Dentists dentist;
	private Receptionist receptionist;
	private Client patient;
	private ArrayList<Used_supplies> used_supp;
	
	
	public Appointment() {
		super();
		this.used_supp= new ArrayList<Used_supplies>();
	}


	public Appointment(Integer app_id, Date app_date, Integer app_duration, Integer app_room, Double app_price,
			String app_treatment, Dentists dentist, Receptionist receptionist, Client patient,
			ArrayList<Used_supplies> used_supp) {
		super();
		this.app_id = app_id;
		this.app_date = app_date;
		this.app_duration = app_duration;
		this.app_room = app_room;
		this.app_price = app_price;
		this.app_treatment = app_treatment;
		this.dentist = dentist;
		this.receptionist = receptionist;
		this.patient = patient;
		this.used_supp = used_supp;
	}


	@Override
	public int hashCode() {
		return Objects.hash(app_id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(app_id, other.app_id);
	}


	@Override
	public String toString() {
		return "Appointment [app_id=" + app_id + ", app_date=" + app_date + ", app_duration=" + app_duration
				+ ", app_room=" + app_room + ", app_price=" + app_price + ", app_treatment=" + app_treatment + "]";
	}


	public Integer getApp_id() {
		return app_id;
	}


	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}


	public Date getApp_date() {
		return app_date;
	}


	public void setApp_date(Date app_date) {
		this.app_date = app_date;
	}


	public Integer getApp_duration() {
		return app_duration;
	}


	public void setApp_duration(Integer app_duration) {
		this.app_duration = app_duration;
	}


	public Integer getApp_room() {
		return app_room;
	}


	public void setApp_room(Integer app_room) {
		this.app_room = app_room;
	}


	public Double getApp_price() {
		return app_price;
	}


	public void setApp_price(Double app_price) {
		this.app_price = app_price;
	}


	public String getApp_treatment() {
		return app_treatment;
	}


	public void setApp_treatment(String app_treatment) {
		this.app_treatment = app_treatment;
	}


	public Dentists getDentist() {
		return dentist;
	}


	public void setDentist(Dentists dentist) {
		this.dentist = dentist;
	}


	public Receptionist getReceptionist() {
		return receptionist;
	}


	public void setReceptionist(Receptionist receptionist) {
		this.receptionist = receptionist;
	}


	public Client getPatient() {
		return patient;
	}


	public void setPatient(Client patient) {
		this.patient = patient;
	}


	public ArrayList<Used_supplies> getUsed_supp() {
		return used_supp;
	}


	public void setUsed_supp(ArrayList<Used_supplies> used_supp) {
		this.used_supp = used_supp;
	}
	
	
	
	
	
}
