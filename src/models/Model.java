package models;

import java.io.IOException;

//cala logika
//informuje widok o tym ze sie zmienily wartosci
//logika zmieniajaca stan modelu jest tutaj
public class Model {
	private char[] finger1;
	private char[] finger2;
	private char[] finger3;
	private char[] finger4;
	private char[] finger5;
	
	private Tree tree;

		
	public Model(String f1, String f2, String f3, String f4, String f5) {
		super();
				
		this.finger1 = f1.toCharArray();
		this.finger2 = f2.toCharArray();
		this.finger3 = f3.toCharArray();
		this.finger4 = f4.toCharArray();
		this.finger5 = f5.toCharArray();
		
		this.tree = new Tree(finger1, finger2, finger3, finger4, finger5);		
		try {				
		//	this.tree.addDictionary2Tree("small.txt");
		//	this.tree.addDictionary2Tree("odm_bezPL.txt");		
			this.tree.addDictionary2Tree("odm.txt");
		
		//	this.tree.saveTree(this.tree, "big");
		//	this.tree = this.tree.loadTree("big");	
					
		} catch (IOException e) {
			e.printStackTrace();
		} 
//			catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	
	public Model(String ...strings) {
		
		for (String c : strings) {
	        System.out.print("|" + c + "|");
	    }
		
	}
	
	// zwraca odpowiednia litere w zaleznosci od ilosci klikniec klawisza
	public char getKey(int finger, int keyPressCount) {		
		switch(finger) {
		case 1:
			keyPressCount = ((keyPressCount - 1) % finger1.length); 
			return finger1[keyPressCount];
		case 2:
			keyPressCount = ((keyPressCount - 1) % finger2.length); 
			return finger2[keyPressCount];
		case 3:
			keyPressCount = ((keyPressCount - 1) % finger3.length); 
			return finger3[keyPressCount];
		case 4:
			keyPressCount = ((keyPressCount - 1) % finger4.length); 
			return finger4[keyPressCount];
		case 5:
			keyPressCount = ((keyPressCount - 1) % finger5.length); 
			return finger5[keyPressCount];
		default:
			System.out.println("Z³y argument przy pobieraniu litery");
			return 'X';
		}
	}

	
	public Tree getTree() {
		return this.tree;
	}
	



}
