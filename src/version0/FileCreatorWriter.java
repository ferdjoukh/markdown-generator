package version0;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 * Class for creating a file and writing in it.
 * 
 * <p>
 * Uses the class java.io.Printwriter.
 * </p>
 * 
 * @author Jona Schmatzler
 */
public class FileCreatorWriter {
	
	private PrintWriter writer;
	
	/**
	 * Constructor of the class FileCreatorWriter.
	 * 
	 * @param pathToFile
	 * 					path to the folder containing the file to create (or to reset to zero).
	 * @param name
	 * 					name of the file to create (or to reset to zero).
	 * @param charset
	 * 					name of a supported charset (ex: "UTF-8").
	 * 
	 * @throws FileNotFoundException
	 * 					if the given path+name does not denote an existing, writableregular file 
	 * 					and a new regular file of that name cannot becreated, or if some other error 
	 * 					occurs while opening or creating the file.
	 * @throws UnsupportedEncodingException
	 * 					if the named charset is not supported
	 */
	public FileCreatorWriter(String pathToFile, String name, String charset) throws FileNotFoundException, UnsupportedEncodingException {
		this.writer = new PrintWriter(pathToFile+"/"+name, charset);
	}
	
	/**
	 * Writes a line in the file the PrintWriter prints to.
	 * 
	 * @param s
	 * 					String to write to the file.
	 */
	public void writeLine(String s) {
		writer.println(s);
		writer.flush();
	}
	
	/**
	 * Closes the PrintWriter.
	 */
	public void close() {
		writer.close();
	}
	
}
