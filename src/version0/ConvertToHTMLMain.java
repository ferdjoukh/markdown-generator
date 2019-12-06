package version0;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class for converting a file from Markdown to HTML.
 * 
 * <p>
 * Very basic for now. 
 * To implement : just indicate repository of the guard page, and convert all .md files from there (checking subfolders etc).
 * </p>
 * 
 * @author Jona Schmatzler
 */
public class ConvertToHTMLMain {
	
	/**
	 * Main method.
	 * 
	 * @param args
	 * 
	 * @throws IOException
	 * 						if an I/O error occurs reading from the file or a malformed orunmappable byte sequence is read.
	 */
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Path to .md file to convert ?");
		String path = sc.nextLine();
		path = sc.nextLine();
		
		MarkdownToHTMLConverter mc = new MarkdownToHTMLConverter(path);
		
		mc.convert();
		
		mc.close();
		sc.close();

	}

}
