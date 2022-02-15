package dao;

import models.Food;
import utils.FileLogger;
import web.ConnectionManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The FoodRepo  is the intermediary between our ORM and Food Servlet.
 */
public class FoodRepo implements IFoodRepo
{
	private Repository repo;

	public FoodRepo()
	{
		repo = new Repository(ConnectionManager.getConnection());
	}

	/**
	 * create food calls the create method in our ORM and creates a Food object
	 * @param food  passes an instance of our Food class.
	 */
	public void createFood(Food food)
	{
		try
		{
			repo.create(food);
		}
		catch(SQLException | IllegalAccessException e)
		{
			FileLogger.getFileLogger().log(e);
		}

	}

	/**
	 * read food calls the read method in our ORM.
	 * @param food  passes an instance of our Food class.
	 * @return returns a list of all food.
	 */
	public List<Food> readFood(Food food)
	{
		List<Food> foodList = new ArrayList<>();
		try
		{
			List<Object> list = repo.read(food);
			for(Object o : list)
			{
				foodList.add((Food) o);
			}
		}
		catch(SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e)
		{
			FileLogger.getFileLogger().log(e);
		}
		return foodList;
	}

	/**
	 * update food calls the update method in our ORM and updates that passed food object
	 * @param food  the food object is passed to the method.
	 */
	public void updateFood(Food food)
	{
		try
		{
			repo.update(food);
		}
		catch(SQLException | IllegalAccessException e)
		{
			FileLogger.getFileLogger().log(e);
		}
	}

	/**
	 * Delete Food deletes the food object from our list of foods.
	 * @param food   the food object to be deleted is passed to the delete method.
	 */
	public void deleteFood(Food food)
	{
		try
		{
			repo.delete(food);
		}
		catch(SQLException | IllegalAccessException e)
		{
			FileLogger.getFileLogger().log(e);
		}
	}

}
