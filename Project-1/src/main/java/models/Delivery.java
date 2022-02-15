package models;

import annotations.Column;
import annotations.Table;
import enums.SQLType;

// Delivery POJO
@Table(tableName = "deliveries")
public class Delivery
{
	@Column(type = SQLType.INT)
	private int deliveries_id;

	@Column(type = SQLType.VARCHAR)
	private String address;

	@Column(type = SQLType.VARCHAR)
	private String food_order;

	public Delivery()
	{
	}

	public Delivery(String address, String food_order)
	{
		this.address = address;
		this.food_order = food_order;
	}

	public int getDeliveries_id()
	{
		return deliveries_id;
	}

	public void setDeliveries_id(int deliveries_id)
	{
		this.deliveries_id = deliveries_id;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getFoodOrder()
	{
		return food_order;
	}

	public void setFoodOrder(String food_order)
	{
		this.food_order = food_order;
	}
}