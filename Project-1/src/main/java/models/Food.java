package models;

import annotations.Column;
import annotations.Table;
import enums.SQLType;

// Food POJO
@Table(tableName = "food")
public class Food
{
	@Column(type = SQLType.INT)
	private int food_id;

	@Column(type = SQLType.VARCHAR)
	private String name;

	@Column(type = SQLType.NUMERIC)
	private double price;

	@Column(type = SQLType.VARCHAR)
	private String description;

	@Column(type = SQLType.VARCHAR)
	private String url;

	public Food()
	{
	}

	public Food(String name, double price, String description, String url)
	{
		this.name = name;
		this.price = price;
		this.description = description;
		this.url = url;
	}

	public int getFoodId()
	{
		return food_id;
	}

	public void setFoodId(int id)
	{
		this.food_id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getPrice()
	{
		return this.price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUrl()
	{
		return this.url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}


}
