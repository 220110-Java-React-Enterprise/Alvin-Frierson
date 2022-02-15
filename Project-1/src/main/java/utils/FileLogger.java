package utils;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FileLogger is a utility class used to log messages/text.
 */
public class  FileLogger
{
	private static FileLogger fileLogger;
	private static String filePath;
	private static boolean consoleOutput;
	private static int stackTraceSize;

	private FileLogger()
	{
		filePath = "C:\\Users\\Jeffrey Lor\\Desktop\\Team-H\\Project-1\\logs\\project1.log";
		consoleOutput = false;
		stackTraceSize = 10;
	}

	public static FileLogger getFileLogger()
	{
		if(fileLogger == null)
		{
			fileLogger = new FileLogger();
		}
		return fileLogger;
	}

	public static FileLogger getFileLogger(String path)
	{
		if(fileLogger == null)
		{
			fileLogger = new FileLogger();
		}
		filePath = path;
		return fileLogger;
	}

	public static String getFilePath()
	{
		return filePath;
	}

	public static void setFilePath(String filePath)
	{
		FileLogger.filePath = filePath;
	}

	public void log(Exception e)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getTimeStamp())
				.append(" - ")
				.append(e.getMessage())
				.append("\n")
				.append(formatStackTrace(e));
		writeToLog(sb.toString());
	}

	public void log(String str)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getTimeStamp())
				.append(" - ")
				.append(str)
				.append("\n");
		writeToLog(sb.toString());
	}

	private static String getTimeStamp()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm:ss]");
		return formatter.format(LocalDateTime.now());
	}

	private static String formatStackTrace(Exception e)
	{
		StackTraceElement[] stackTrace = e.getStackTrace();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < stackTraceSize; i++)
		{
			if(i >= stackTrace.length)
			{
				break;
			}
			sb.append("\t");
			sb.append(stackTrace[i]);
			sb.append("\n");
		}
		return sb.toString();
	}

	private static void writeToLog(String text)
	{
		try(FileWriter fileWriter = new FileWriter(getFilePath(), true))
		{
			fileWriter.write(text);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
