package version0;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Class for creating and writing to a Markdown file.
 * 
 * <p>
 * Extends the FileCreatorWriter class.
 * </p>
 * 
 * @author Jona Schmatzler
 */
public class MarkdownCreatorWriter extends FileCreatorWriter {
	
	/**
	 * Constructor of theclass MardownCreatorWriter.
	 * 
	 * @param pathToFile
	 * 					path to the folder containing the file to create (or to reset to zero).
	 * @param name
	 * 					name of the file to create (or to reset to zero).
	 * 
	 * @throws FileNotFoundException
	 * 					if the given path+name does not denote an existing, writableregular file 
	 * 					and a new regular file of that name cannot becreated, or if some other error 
	 * 					occurs while opening or creating the file.
	 * @throws UnsupportedEncodingException
	 * 					if "UTF-8" isn't a supported charset.
	 */
	public MarkdownCreatorWriter(String pathToFile, String name) throws FileNotFoundException, UnsupportedEncodingException {
		super(pathToFile, name+".md", "UTF-8");
	}
	
	/**
	 * Writes a title of a certain importance to the file.
	 * 
	 * @param title
	 * 					content of the title.
	 * @param ipt
	 * 					importance of the title. Int from 1 to 4.
	 */
	public void writeTitle(String title, int ipt) {
		String toAdd = "";
		switch(ipt) {
		case 1: toAdd = "#";
		case 2: toAdd = "##";
		case 3: toAdd = "###";
		case 4: toAdd = "####";
		default: toAdd = "####";
		}
		super.writeLine(toAdd+" "+title);
	}
	
}