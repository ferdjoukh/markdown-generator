package version0;

import java.io.IOException;

/**
 * Test class for the MarkdownToHTMLConverter class.
 * 
 * @author Jona Schmatzler
 */
public class TestConverter {
	
	/**
	 * Main method.
	 * 
	 * @param args
	 * 
	 * @throws IOException
	 * 						if an I/O error occurs reading from the file or a malformed orunmappable byte sequence is read.
	 */
	public static void main(String[] args) throws IOException {
		
		String path = "C:/Users/Jona Schmatzler/Documents/Java/MarkdownGenerator/markdown-generator/the-file-name.txt";
		
		MarkdownToHTMLConverter a = new MarkdownToHTMLConverter(path);
		
		for(String s : a.getLines()) {
			System.out.println(s);
		}
		
		a.convert();
	}

}
