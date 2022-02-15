package scriptors;

import annotations.Table;

import java.lang.reflect.Field;

/**
 * Object Relational Mapping class used to convert data between incompatible type systems using an OOP based language.
 */
public class Scriptor
{

	public static String createSQL(Object obj)
	{
		// INSERT INTO table (_, _, _) VALUES (?, ?, ?)

		String part1 = "INSERT INTO ";
		String tableName = obj.getClass().getAnnotation(Table.class).tableName();
		String part2 = " (";
		String columnList = "";
		String part3 = ") VALUES (";
		String values = "";
		String part4 = ")";

		Field[] fields = obj.getClass().getDeclaredFields();
		for(int i = 1; i < fields.length; i++) {
			columnList += fields[i].getName();
			values += "?";

			// If last, then don't put comma spacing
			if(i != fields.length - 1) {
				columnList += ", ";
				values += ", ";
			}
		}

		return part1 + tableName + part2 + columnList + part3 + values + part4;
	}

	public static String readSQL(Object obj)
	{
		// SELECT * FROM table

		String select = "SELECT * FROM ";
		String tableName = obj.getClass().getAnnotation(Table.class).tableName();

		return select + tableName;
	}

	public static String updateSQL(Object obj)
	{
		// UPDATE table SET _ = ? WHERE _ = ?

		String part1 = "UPDATE ";
		String tableName = obj.getClass().getAnnotation(Table.class).tableName();
		String part2 = " SET ";
		String setValues = "";
		String part3 = " WHERE ";
		String id = tableName + "_id = ?";

		Field[] fields = obj.getClass().getDeclaredFields();
		for(int i = 1; i < fields.length; i++) {
			setValues += fields[i].getName() + " = ?";

			// If last, then don't put comma spacing
			if(i != fields.length - 1) {
				setValues += ", ";
			}
		}

		return part1 + tableName + part2 + setValues + part3 + id;
	}

	public static String deleteSQL(Object obj)
	{
		// DELETE FROM table WHERE _id = ?

		String part1 = "DELETE FROM ";
		String tableName = obj.getClass().getAnnotation(Table.class).tableName();
		String part2 = " WHERE ";
		String id = tableName + "_id = ?";

		return part1 + tableName + part2 + id;
	}

}
