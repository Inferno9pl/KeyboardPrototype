

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import models.Tree;

public class Dictionary {
	private Tree tree;
	
	public Dictionary(Tree tree) {
		this.tree = tree;
	}
	
	public Tree addDictionary2Tree(String name) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(name), "UTF-8"));
		try {
			String line = br.readLine();
			while (line != null) {	
				String[] temp = line.split(", ");
				for(int i = 0; i < temp.length; i++) {			
//					System.out.println("dla:   " + temp[i].toLowerCase());						
					this.tree.addWord(temp[i].toLowerCase());
				}							
				line = br.readLine();
			}
			return this.tree;
		} finally {
			br.close();
			System.out.println("Pobrano slownik z pliku i stworzono drzewo z niego");
		}
	}
}
