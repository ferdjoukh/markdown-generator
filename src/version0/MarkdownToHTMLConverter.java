package version0;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for converting a Markdown file to HTML file.
 * 
 * <p>
 * For now, very basic. Balises that are implemented are the following :
 * <ul>
 * <li>paragraphs</li>
 * <li>emphasis (em, strong)</li>
 * <li>links (href)</li>
 * <li>unordered lists</li>
 * <li>titles of importance 1 to 4.</li>
 * </ul>
 * Eventually to improve.
 * </p>
 * 
 * @author Jona Schmatzler
 */
public class MarkdownToHTMLConverter {
	
	/**
	 * Lines of the file to convert.
	 */
	private List<String> lines;
	/**
	 * Instance of HTMLCreatorWriter used to write to the resulting html file.
	 */
	private HTMLCreatorWriter htmlcw;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param pathToFile
	 * 						String indication path to the file. Ex: "C:/Jona/Files/file.md".
	 * @throws IOException
	 * 						if an I/O error occurs reading from the file or a malformed orunmappable byte sequence is read.
	 */
	public MarkdownToHTMLConverter(String pathToFile) throws IOException {
		//getting the lines of the file and loading them into this.lines
		this.lines = Files.readAllLines(new File(pathToFile).toPath(), Charset.forName("utf-8"));
		String path = "";
		String name = "";
		if (pathToFile.lastIndexOf("/") > 0) {
			path = pathToFile.substring(0,pathToFile.lastIndexOf("/"));
			name = pathToFile.substring(pathToFile.lastIndexOf("/"), pathToFile.lastIndexOf("."));
		} else {
			name = pathToFile.substring(0, pathToFile.lastIndexOf(".")-1);
		}
		this.htmlcw = new HTMLCreatorWriter(path, name);
	}
	
	/**
	 * Returns lines of the file to convert. Used to test.
	 * 
	 * @return lines of the file to convert.
	 */
	public List<String> getLines() {
		return this.lines;
	}
	
	/**
	 * Writes the html conversion of the md file to an html file in the same repository with the same name.
	 */
	public void convert() {
		boolean paragraphBegun = false;
		boolean listBegun = false;
		ArrayList<String> paragraphLines = new ArrayList<String>();
		ArrayList<String> listLines = new ArrayList<String>();
		for(String s : this.lines) {
			if (s.contentEquals("")) {
				/*if (paragraphBegun) {
					this.htmlcw.writeParagraph(paragraphLines);
					paragraphBegun = false;
					paragraphLines.clear();
				} else if (listBegun) {
					this.htmlcw.writeList(listLines);
					listBegun = false;
					listLines.clear();
				} else {
					paragraphBegun = true;
				}*/
				if (!paragraphBegun) {
					this.htmlcw.startParagraph();
					if (listBegun) {
						this.htmlcw.startList(false);
						this.htmlcw.writeList(listLines);
						this.htmlcw.endList(false);
						listLines.clear();
						listBegun = false;
					}
				} else {
					paragraphBegun = false;
					this.htmlcw.writeParagraph(paragraphLines);
					paragraphLines.clear();
					this.htmlcw.endParagraph();
					if (listBegun) {
						this.htmlcw.startList(false);
						this.htmlcw.writeList(listLines);
						this.htmlcw.endList(false);
						listLines.clear();
						listBegun = false;
					}
				}
			} else {
				switch(s.substring(0, 1)) {
				//first case : it's a title
				case "#": {
					if (s.substring(0,4).contentEquals("####")) {
						this.htmlcw.writeTitle(s.substring(4), 4);
					} else if (s.substring(0,3).contentEquals("###")) {
						this.htmlcw.writeTitle(s.substring(3), 3);
					} else if (s.substring(0,2).contentEquals("##")) {
						this.htmlcw.writeTitle(s.substring(2), 2);
					} else {
						this.htmlcw.writeTitle(s.substring(1), 1);
					}
					break;
				}
				
				//third case : it's an unordered list
				case "*": {
					if(paragraphBegun) {
						this.htmlcw.writeParagraph(paragraphLines);
						paragraphLines.clear();
					}
					listLines.add(s.substring(1));
					listBegun = true;
					break;
				}
				
				//fourth case : default
				default: {
					if (listBegun) {
						this.htmlcw.startList(false);
						this.htmlcw.writeList(listLines);
						this.htmlcw.endList(false);
						listBegun = false;
						listLines.clear();
					}
					paragraphBegun = true;
					paragraphLines.add(s);
				}
				}
			}
		}
		if (!paragraphBegun) {
			this.htmlcw.startParagraph();
			if (listBegun) {
				this.htmlcw.startList(false);
				this.htmlcw.writeList(listLines);
				this.htmlcw.endList(false);
				listLines.clear();
				listBegun = false;
			}
		} else {
			paragraphBegun = false;
			this.htmlcw.writeParagraph(paragraphLines);
			paragraphLines.clear();
			this.htmlcw.endParagraph();
			if (listBegun) {
				this.htmlcw.startList(false);
				this.htmlcw.writeList(listLines);
				this.htmlcw.endList(false);
				listLines.clear();
				listBegun = false;
			}
		}
	}
	
	/**
	 * Closes the instance of HTMLCreatorWriter.
	 */
	public void close() {
		this.htmlcw.close();
		this.lines.clear();
	}
	
}