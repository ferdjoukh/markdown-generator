package version0;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Class for creating and writing to a HTML file.
 * 
 * <p>
 * Extends the FileCreatorWriter class.
 * </p>
 * 
 * @author Jona Schmatzler
 */
public class HTMLCreatorWriter extends FileCreatorWriter {
	
	/**
	 * Constructor of the class HTMLCreatorWriter.
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
	public HTMLCreatorWriter(String pathToFile, String name) throws FileNotFoundException, UnsupportedEncodingException {
		super(pathToFile, name+".html", "UTF-8");
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
		super.writeLine("<h"+ipt+">"+title+"</h"+ipt+">");
	}
	
	/**
	 * Writes a paragraph to the file.
	 * 
	 * @param lines
	 * 					ArrayList of the Strings in the paragraph.
	 */
	public void writeParagraph(ArrayList<String> lines) {
		for(String s : lines) {
			this.writeLine(s);
		}
	}
	
	/**
	 * Starts a paragraph in html file. Writes corresponding balise to the file.
	 * 
	 */
	public void startParagraph() {
		super.writeLine("<p>");
	}
	
	/**
	 * Ends a paragraph in html file. Writes corresponding balise to the file.
	 * 
	 */
	public void endParagraph() {
		super.writeLine("</p>");
	}
	
	/**
	 * Writes a list to the file.
	 * 
	 * @param lines
	 * 					ArrayList of the elements of the list.
	 */
	public void writeList(ArrayList<String> lines) {
		for(String s : lines) {
			super.writeLine("<li>");
			this.writeLine(s);
			super.writeLine("</li>");
		}
	}
	
	/**
	 * Starts a list in the html file. Writes corresponding balise to the file.
	 * 
	 * @param u
	 * 					true if ordered, false if unordered list.
	 * 
	 */
	public void startList(boolean u) {
		if (u) {
			super.writeLine("<ol>");
		} else {
			super.writeLine("<ul>");
		}
	}
	
	/**
	 * Ends a list in the html file. Writes corresponding balise to the file.
	 * 
	 * @param u
	 * 					true if ordered, false if unordered list.
	 * 
	 */
	public void endList(boolean u) {
		if (u) {
			super.writeLine("</ol>");
		} else {
			super.writeLine("</ul>");
		}
	}
	
	/**
	 * Writes a single line to the html file.
	 * 
	 * <p>
	 * We check for _ balises, __ balises and link balises [bla](bla.html)
	 * </p>
	 * 
	 * @param s
	 * 					line that is written to the html file.
	 */
	public void writeLine(String s) {
		boolean underscoreOpened = false;
		boolean doubleUnderscoreOpened = false;
		String toWrite = "";
		int i;
		for(i = 0; i < s.length()-1; i++) {
			char a = s.charAt(i);
			if (a == '_') {
				if (s.charAt(i+1) == '_') {
					i++;
					if (doubleUnderscoreOpened) {
						toWrite += "</strong>";
					} else {
						toWrite += "<strong>";
					}
					doubleUnderscoreOpened = !doubleUnderscoreOpened;
				} else {
					if (underscoreOpened) {
						toWrite += "</em>";
					} else {
						toWrite += "<em>";
					}
					underscoreOpened = !underscoreOpened;
				}
			} else if (a == '[') {
				if (s.substring(i).contains("](") && s.substring(i+s.substring(i).indexOf("](")).contains(")")) {
					String title = s.substring(i+1,i+s.substring(i).indexOf("]("));
					String href = s.substring(i+s.substring(i).indexOf("](")+2,
							i+s.substring(i).indexOf("](")+2+s.substring(i+s.substring(i).indexOf("](")+2).indexOf(")"));
					//String href = s.substring(i+s.substring(i).indexOf("](")+2,i+s.substring(i+
					//		s.substring(i).indexOf("](")+2+s.substring(i).indexOf("](")).indexOf(")"));
					toWrite += "<a href="+href+">"+title+"</a>";
					i += s.substring(i).indexOf("](")+2+s.substring(i+s.substring(i).indexOf("](")+2).indexOf(")");
				} else {
					toWrite += a;
				}
			} else {
				toWrite += a;
			}
		}
		if (i == s.length()-1) {
			toWrite += s.charAt(s.length()-1);
		}
		super.writeLine(toWrite);
	}
	
}
