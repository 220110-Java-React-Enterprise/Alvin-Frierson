package dao;

import annotations.Column;
import enums.SQLType;
import scriptors.Scriptor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes SQL scripts written by the Scriptor class on a given connection
 */
public class Repository
{
	private Connection connection;

	public Repository(Connection connection)
	{
		this.connection = connection;
	}

	public Connection getConnection()
	{
		return connection;
	}

	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

	public void create(Object obj) throws SQLException, IllegalAccessException
	{
		PreparedStatement pstmt = connection.prepareStatement(Scriptor.createSQL(obj), Statement.RETURN_GENERATED_KEYS);

		Field[] fields = obj.getClass().getDeclaredFields();
		for(int i = 1; i < fields.length; i++) {
			fields[i].setAccessible(true);
			SQLType type = fields[i].getAnnotation(Column.class).type();
			switch(type) {
			case VARCHAR:
				pstmt.setString(i, (String)fields[i].get(obj));
				break;
			case INT:
				pstmt.setInt(i, (Integer)fields[i].get(obj));
				break;
			case NUMERIC:
				pstmt.setDouble(i, (Double)fields[i].get(obj));
				break;
			case BOOL:
				pstmt.setBoolean(i, (Boolean)fields[i].get(obj));
				break;
			case BIGINT:
				pstmt.setLong(i, (Long)fields[i].get(obj));
				break;
			}
			fields[i].setAccessible(false);
		}

		pstmt.execute();
	}

	public List<Object> read(Object obj) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
	{
		PreparedStatement pstmt = connection.prepareStatement(Scriptor.readSQL(obj), Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = pstmt.executeQuery();

		List<Object> list = new ArrayList<>();

		while(rs.next()) {
			Object o = obj.getClass().getConstructor().newInstance();

			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field field : fields)
			{
				field.setAccessible(true);
				field.set(o, rs.getObject(field.getName()));
				field.setAccessible(false);
			}
			list.add(o);
		}

		return list;
	}

	public void update(Object obj) throws SQLException, IllegalAccessException
	{
		PreparedStatement pstmt = connection.prepareStatement(Scriptor.updateSQL(obj), Statement.RETURN_GENERATED_KEYS);

		Field[] fields = obj.getClass().getDeclaredFields();
		int i;
		for(i = 1; i < fields.length; i++)
		{
			fields[i].setAccessible(true);
			SQLType type = fields[i].getAnnotation(Column.class).type();
			switch(type) {
			case VARCHAR:
				pstmt.setString(i, (String)fields[i].get(obj));
				break;
			case INT:
				pstmt.setInt(i, (Integer)fields[i].get(obj));
				break;
			case NUMERIC:
				pstmt.setDouble(i, (Double)fields[i].get(obj));
				break;
			case BOOL:
				pstmt.setBoolean(i, (Boolean)fields[i].get(obj));
				break;
			case BIGINT:
				pstmt.setLong(i, (Long)fields[i].get(obj));
				break;
			}
			fields[i].setAccessible(false);
		}
		// set _id
		fields[0].setAccessible(true);
		pstmt.setInt(i, (Integer)fields[0].get(obj));
		fields[0].setAccessible(false);

		pstmt.executeUpdate();
	}

	public void delete(Object obj) throws SQLException, IllegalAccessException
	{
		PreparedStatement pstmt = connection.prepareStatement(Scriptor.deleteSQL(obj), Statement.RETURN_GENERATED_KEYS);
		Field[] fields = obj.getClass().getDeclaredFields();
		fields[0].setAccessible(true);
		pstmt.setInt(1, (Integer)fields[0].get(obj));
		fields[0].setAccessible(false);

		pstmt.execute();
	}

}
