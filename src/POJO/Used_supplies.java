package POJO;

import java.io.Serializable;

public class Used_supplies implements Serializable {

	private static final long serialVersionUID = -5978065005847328945L;
	private Integer item_id;
	private Integer app_id;
	private Integer used_supp_id;
	private Integer amount;

	public Used_supplies(Integer item_id, Integer app_id, Integer used_supp_id, Integer amount) {
		super();
		this.item_id = item_id;
		this.app_id = app_id;
		this.used_supp_id = used_supp_id;
		this.amount = amount;
	}

	public Used_supplies() {
		super();
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getApp_id() {
		return app_id;
	}

	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}

	public Integer getUsed_supp_id() {
		return used_supp_id;
	}

	public void setUsed_supp_id(Integer used_supp_id) {
		this.used_supp_id = used_supp_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((used_supp_id == null) ? 0 : used_supp_id.hashCode());
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
		if (used_supp_id == null) {
			if (other.used_supp_id != null)
				return false;
		} else if (!used_supp_id.equals(other.used_supp_id))
			return false;
		return true;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "\n[Id=" + used_supp_id + ",  Supply(id)=" + item_id + ",  Appointment(id)=" + app_id + ",  Amount="
				+ amount + "]";
	}

}
