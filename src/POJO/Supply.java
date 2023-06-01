package POJO;

import java.io.Serializable;

import java.util.Objects;

public class Supply implements Serializable {

	private static final long serialVersionUID = -2196147451131341836L;

	private Integer item_id;
	private String item_name;
	private Integer item_amount;

	public Supply(Integer item_id, String item_name, Integer item_amount) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_amount = item_amount;
	}

	public Supply(String item_name, Integer item_amount) {
		super();
		this.item_name = item_name;
		this.item_amount = item_amount;
	}

	public Supply() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(item_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supply other = (Supply) obj;
		return Objects.equals(item_id, other.item_id);
	}

	@Override
	public String toString() {
		return "\n[Id=" + item_id + ", Name=" + item_name + ", Amount=" + item_amount + "]";
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public Integer getItem_amount() {
		return item_amount;
	}

	public void setItem_amount(Integer item_amount) {
		this.item_amount = item_amount;
	}

}
