package version0;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class for creating folders.
 * 
 * @author Jona Schmatzler
 */
public class FolderCreator {
	
	/**
	 * Creates a folder.
	 * 
	 * @param path
	 * 					path to folder to create.
	 * 
	 * @throws IOException
	 * 					if an I/O error occurs or the parent directory does not exist.
	 */
	public void createFolder(String path) throws IOException {
		Files.createDirectory(Paths.get(path));
	}
	
}
