package POJO;

import java.io.Serializable;

public class Order_supplies implements Serializable{

	private static final long serialVersionUID = 4505893739020297802L;

	private Supply item;
	private Dentists doc;
	private Integer id;
	
	public Order_supplies() {
		super();
	}

	public Order_supplies(Supply item, Dentists doc, Integer id) {
		super();
		this.item = item;
		this.doc = doc;
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Order_supplies other = (Order_supplies) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order_supplies [id=" + id + "]";
	}

	public Supply getItem() {
		return item;
	}

	public void setItem(Supply item) {
		this.item = item;
	}

	public Dentists getDoc() {
		return doc;
	}

	public void setDoc(Dentists doc) {
		this.doc = doc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
