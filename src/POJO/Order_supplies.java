package POJO;

import java.io.Serializable;
import java.sql.Date;

public class Order_supplies implements Serializable {

	
	private static final long serialVersionUID = 4505893739020297802L;

	private Integer order_supp_id;
	 private Integer item_id;
	 private Integer doc_id;
	 private Integer amount;
	 private Date date;
	 private Integer supplierId;
	 
	public Order_supplies(Integer order_supp_id, Integer item_id, Integer doc_id,Integer amount, Date date, Integer supplierId) {
		super();
		this.order_supp_id = order_supp_id;
		this.item_id = item_id;
		this.doc_id = doc_id;
		this.amount=amount;
		this.date = date;
		this.supplierId = supplierId;
	}


	public Order_supplies(Integer item_id, Integer doc_id, Integer amount, Date date, Integer supplierId) {
		super();
		this.item_id = item_id;
		this.doc_id = doc_id;
		this.amount = amount;
		this.date = date;
		this.supplierId = supplierId;
	}


	public Order_supplies() {
		super();
	}

	public Integer getOrder_supp_id() {
		return order_supp_id;
	}

	public void setOrder_supp_id(Integer order_supp_id) {
		this.order_supp_id = order_supp_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(Integer doc_id) {
		this.doc_id = doc_id;
	}

	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_supp_id == null) ? 0 : order_supp_id.hashCode());
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
		if (order_supp_id == null) {
			if (other.order_supp_id != null)
				return false;
		} else if (!order_supp_id.equals(other.order_supp_id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Order_supplies [order_supp_id=" + order_supp_id + ", item_id=" + item_id + ", doc_id=" + doc_id
				+ ", amount=" + amount + ", date=" + date + ", supplierId=" + supplierId + "\n]";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	
	 
	 
}
