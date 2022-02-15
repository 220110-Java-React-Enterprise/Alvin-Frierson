package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FoodRepo;
import dao.IFoodRepo;
import models.Food;
import utils.FileLogger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The Food Servlet extends the HttpServlet and establishes our HTTP protocol
 */
public class FoodServlet extends HttpServlet
{
	private IFoodRepo foodRepo;

	public FoodServlet()
	{
		super();
		try
		{
			foodRepo = new FoodRepo();
		}
		catch(Exception e)
		{
			FileLogger.getFileLogger().log(e);
		}
	}

	/**
	 * The doGet method performs a GET HTTP verb.
	 * @param req    the get request body is passed to the method from the client
	 * @param resp	 the response body is passed to the method from the server
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			List<Food> foodList = foodRepo.readFood(new Food());
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(foodList);
			resp.getWriter().print(jsonString);
			resp.setStatus(200);
		}
		catch(Exception e)
		{
			FileLogger.getFileLogger().log(e);
			resp.setStatus(500);
		}

	}

	/**
	 * The doPost method perform a POST HTTP verb.
	 * @param req    the get request body is passed to the method by the client
	 * @param resp	 the response body is passed to the method from the server
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			Food payload = mapper.readValue(req.getInputStream(), Food.class);
			foodRepo.createFood(payload);
			resp.setStatus(203);
		}
		catch(Exception e)
		{
			FileLogger.getFileLogger().log(e);
			resp.setStatus(500);
		}

	}

	/**
	 * The doPut method perform a PUT HTTP verb.
	 * @param req    the get request body is passed to the method from the client.
	 * @param resp	 the response body is passed to the method from the server.
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			Food payload = mapper.readValue(req.getInputStream(), Food.class);
			foodRepo.updateFood(payload);
			resp.setStatus(203);
		}
		catch(Exception e)
		{
			FileLogger.getFileLogger().log(e);
			resp.setStatus(500);
		}

	}

	/**
	 * The doDelete method performs the DELETE HTTP verb.
	 * @param req    the get request body is passed to the method from the client.
	 * @param resp	 the response body is passed to the method from the server.
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			Food payload = mapper.readValue(req.getInputStream(), Food.class);
			foodRepo.deleteFood(payload);
			resp.setStatus(203);
		}
		catch(Exception e)
		{
			FileLogger.getFileLogger().log(e);
			resp.setStatus(500);
		}

	}
}
