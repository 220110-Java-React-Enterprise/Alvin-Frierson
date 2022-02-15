package enums;

/**
 * Enumeration for mentioning the specified data types one at a time(one-by-one).
 */
public enum SQLType
{
	BOOL,       //java boolean - 1 bit
	INT,        //java int - 4 byte
	BIGINT,     //java long - 8 byte
	NUMERIC,    //java float/double with selectable precision
	VARCHAR    //variable length character string
}
