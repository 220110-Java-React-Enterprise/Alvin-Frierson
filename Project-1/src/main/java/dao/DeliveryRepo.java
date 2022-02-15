package dao;

import models.Delivery;
import utils.FileLogger;
import web.ConnectionManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Delivery is the intermediary between our ORM and Delivery Servlet.
 */

public class DeliveryRepo implements IDeliveryRepo
{

	private Repository repo;

	public DeliveryRepo()
	{
		repo = new Repository(ConnectionManager.getConnection());
	}

	/**
	 * create delivery calls the create method in our ORM and creates a Delivery object
	 * @param delivery  passes an instance of our Food class.
	 */
	@Override
	public void createDelivery(Delivery delivery)
	{
		try
		{
			repo.create(delivery);
		}
		catch(SQLException | IllegalAccessException e)
		{
			FileLogger.getFileLogger().log(e);
		}
	}

	/**
	 * read deliver calls the read method in our ORM.
	 * @param delivery  passes an instance of our Delivery class.
	 * @return returns a list of all delivery items.
	 */
	@Override
	public List<Delivery> readDelivery(Delivery delivery)
	{
		List<Delivery> deliveryList = new ArrayList<>();
		try
		{
			List<Object> list = repo.read(delivery);
			for(Object o : list)
			{
				deliveryList.add((Delivery) o);
			}
		}
		catch(SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e)
		{
			FileLogger.getFileLogger().log(e);
		}
		return deliveryList;
	}

	/**
	 * update delivery calls the update method in our ORM and updates that passed delivery object
	 * @param delivery  the delivery object is passed to the method.
	 */
	@Override
	public void updateDelivery(Delivery delivery)
	{
		try
		{
			repo.update(delivery);
		}
		catch(SQLException | IllegalAccessException e)
		{
			FileLogger.getFileLogger().log(e);
		}
	}

	/**
	 * Delete Delivery deletes the delivery object from our list of deliveries.
	 * @param delivery   the delivery object to be deleted is passed to the delete method.
	 */
	@Override
	public void deleteDelivery(Delivery delivery)
	{
		try
		{
			repo.delete(delivery);
		}
		catch(SQLException | IllegalAccessException e)
		{
			FileLogger.getFileLogger().log(e);
		}
	}
}
