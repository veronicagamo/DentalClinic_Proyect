package POJO;

import java.io.Serializable;

public class Used_supplies implements Serializable{

	
	private static final long serialVersionUID = 5925360105291710639L;
	private Integer supp_id;
	private Supply item;
	private Appointment app;
	
	public Used_supplies(Integer supp_id, Supply item, Appointment app) {
		super();
		this.supp_id = supp_id;
		this.item = item;
		this.app = app;
	}

	public Used_supplies() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((supp_id == null) ? 0 : supp_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Used_supplies other = (Used_supplies) obj;
		if (supp_id == null) {
			if (other.supp_id != null)
				return false;
		} else if (!supp_id.equals(other.supp_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Used_supplies [supp_id=" + supp_id + "]";
	}

	public Integer getSupp_id() {
		return supp_id;
	}

	public void setSupp_id(Integer supp_id) {
		this.supp_id = supp_id;
	}

	public Supply getItem() {
		return item;
	}

	public void setItem(Supply item) {
		this.item = item;
	}

	public Appointment getApp() {
		return app;
	}

	public void setApp(Appointment app) {
		this.app = app;
	}
	
	
	

}
