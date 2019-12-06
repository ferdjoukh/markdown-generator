package version0;

import java.io.IOException;
import java.util.Scanner;

/**
 * Executable class for the generation of all the markdown files.
 * 
 * @author Jona Schmatzler
 */
public class MarkdownGeneratorMain {
	
	/**
	 * Main.
	 * 
	 * @param args
	 * 
	 * @throws IOException
	 * 					if an I/O error occurs or the parent directory does not exist.
	 */
	public static void main(String[] args) throws IOException {
		MarkdownGenerator mg = new MarkdownGenerator();
		Scanner sc = new Scanner(System.in);
		System.out.println("How many tools?");
		int nbOfTools = sc.nextInt();
		System.out.println("Path for folders ?");
		String path = sc.nextLine();
		path = sc.nextLine();
		mg.createHierarchy(path);
		mg.writeGuardPage(nbOfTools, path);
		for(int i = 1; i <= nbOfTools; i++) {
			mg.writeToolPage(i, path);
		}
		sc.close();
	}

}
