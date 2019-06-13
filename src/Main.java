import java.util.LinkedList;

import controllers.Controller;
import models.Model;
import views.View;

public class Main {
	
	public static void run() {
		int delay = 400;
		
		String f1 = "abcdef";
		String f2 = "ghijkl";
		String f3 = "mnoprs";
		String f4 = "tuwxyz";
		String f5 = " ";
		Model model = new Model(f1, f2, f3, f4, f5);
		View view = new View(model);
		view.setVisible(true);
		Controller controller = new Controller(model, view, delay);
		
		
		//----------
		String f6 = "a¹bcæ";
		String f7 = "deêf";
		String f8 = "ghi";
		String f9 = "jkl³";
		String f10 = "mnñoó";
		String f11 = "pqrsœ";
		String f12 = "tuwv";
		String f13 = "xyz¿Ÿ";
		Model model2 = new Model(f6, f7, f8, f9, f10, f11, f12, f13);
		View view2 = new View(model2);
		view2.setVisible(true);
		Controller controller2 = new Controller(model2, view2, delay);
		
		//----------
		
				
		
	}

	public static void main(String[] args) {
		System.out.println("Working..");
		run();

	}
}
