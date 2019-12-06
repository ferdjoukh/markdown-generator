package version0;

import java.io.IOException;

/**
 * Class for generating the Markdown folders and files.
 * 
 * @author Jona Schmatzler
 */
public class MarkdownGenerator {
	
	/**
	 * Creates the folder hierarchy for the files.
	 * 
	 * @param path
	 * 					path to the folder in which the hierarchy will be created.
	 * 
	 * @throws IOException
	 * 					if an I/O error occurs or the parent directory does not exist.
	 */
	public void createHierarchy(String path) throws IOException {
		FolderCreator fc = new FolderCreator();
		fc.createFolder(path+"/markdown");
		System.out.println("Main folder created.");
		fc.createFolder(path+"/markdown/tools");
		System.out.println("Tool folder created.");
	}
	
	/**
	 * Creates the guardpage for the website.
	 * <p>
	 * Basically just a title, a descriptive paragraph,and a list of links to the various 
	 * tool pages.
	 * </p>
	 * 
	 * @param nbOfTools
	 * 					number of tools the company has.
	 * @param path
	 * 					path to theparent directory.
	 * 
	 * @throws IOException
	 * 					if an I/O error occurs or the parent directory does not exist.
	 */
	public void writeGuardPage(int nbOfTools, String path) throws IOException {
		MarkdownCreatorWriter mcw = new MarkdownCreatorWriter(path+"/markdown","guardpage");
		System.out.println("Guard page created.");
		mcw.writeTitle("TITRE 1", 1);
		mcw.writeLine("      ");
		mcw.writeLine("Description de la page, de l'entreprise, des outils, etc. "
				+ "Pour mettre a jour cette description, simplement ecrire du texte.");
		mcw.writeLine("      ");
		mcw.writeTitle("Liste des outils",2);
		for(int i = 1; i <= nbOfTools; i++) {
			mcw.writeLine("* [Outil "+i+"](tools/tool"+i+".html)");
		}
		mcw.close();
	}
	
	/**
	 * Writes the pages for a tool.
	 * <p>
	 * Basically just a title and a description of the tool.
	 * </p>
	 * 
	 * @param nb
	 * 					number of the tool described.
	 * @param path
	 * 					path to the parent directory.
	 * 
	 * @throws IOException
	 * 					if an I/O error occurs or the parent directory does not exist.
	 */
	public void writeToolPage(int nb, String path) throws IOException {
		MarkdownCreatorWriter mcw = new MarkdownCreatorWriter(path+"/markdown/tools","tool"+nb);
		mcw.writeTitle("OUTIL "+nb, 1);
		mcw.writeLine("      ");
		mcw.writeLine("Description de l'outil. Pour mettre a jour cette "
				+ "description, simplement ecrire du texte.");
		System.out.println("Tool page "+nb+" created.");
		mcw.close();
	}

}
