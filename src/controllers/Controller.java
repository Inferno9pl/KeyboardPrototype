package controllers;

import models.KeyThread;
import models.Model;
import models.Tree.Node;
import views.View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//on przyjmuje czynnosci uzytkownika i reaguje na jego poczynania zarz¹dzajacc aktualizacje modelu i odswiezenie widoku
//uzycie obserwatora do powiadamiania kontrolera o zmianie stanu modelu, czyli obserwator chyba w modelu musi byc
//wszelkie dzialania uzytkownika kieruje na odpowiednie wywolania modelu
public class Controller implements KeyListener {
	private View view;
	private Model model;
	private int delay; // opoznienie miedzy nacisnieciem klawisza

	private int prevKeyI = -1;
	private KeyThread[] keyThread = new KeyThread[5];
	private Thread[] threads = new Thread[5];

	private int mode = 2; // 1 - wpisywanie kazdej litery, 2 - slownik
	private Node actualNode = null;

	private String prevWords = "";
	
	private boolean spaceHold = false;

	public Controller(Model model, View view, int delay) {
		this.model = model;
		this.view = view;
		this.view.addKeyListener(this);
		this.delay = delay;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		
		if(!spaceHold && key == ' ') {
			spaceHold = true;
			System.out.println("HOLD");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == ' ')
			key = '5'; // zamiana spacji na cyfre, aby nie bylo konfliktu z tworzeniem tablicy o takim indeksie
		if ((key == '1') || (key == '2') || (key == '3') || (key == '4') || (key == '5')) {
			int keyI = key - '0'; // zamiana na cyfre

			//pisanie pojedynczymi literami
			if (mode == 1) {
				// jesli podany znak jest inny to konczy watek poprzedniego znaku
				if ((prevKeyI != keyI) && (prevKeyI > 0)) {
					if (threads[prevKeyI - 1].isAlive()) {
						keyThread[prevKeyI - 1].end();
					}
				}

				// tworzenie watkow
				if ((keyThread[keyI - 1] == null) || !threads[keyI - 1].isAlive()) {
					keyThread[keyI - 1] = new KeyThread(view, keyI, delay);
					threads[keyI - 1] = new Thread(keyThread[keyI - 1]);
				}

				// jesli nie jest odpalony to odpala, w innym wypadku dodaje czas
				if (!threads[keyI - 1].isAlive()) threads[keyI - 1].start(); // odpalenie odpowiedniego watku
				else keyThread[keyI - 1].addTime();
				
				prevKeyI = keyI; // zapisanie obecnego znaku
			}

			//slownik
			if (mode == 2) {
				//spacja jako akceptacja wyrazu
				if (keyI == 5) {
					//this.view.print(prevWords);
					actualNode = null;
				}
				
				if (actualNode == null) {
					actualNode = this.model.getTree().getRoot();
					prevWords = "";
				}

				// przejscie do dziecka jesli istnieje
				if (actualNode.getChild(keyI - 1) != null) {
					actualNode = actualNode.getChild(keyI - 1);

					// jesli ma wyrazy to je wypisze
					if (actualNode.hasWords) {
						String temp = actualNode.getWords();
						this.view.print(temp);
						prevWords = temp;
					}
					// jesli nie ma to wypisze poprzednie i doda litere
					else {
						prevWords = prevWords + key;
						this.view.print(prevWords);
					}

				// jesli nie ma dziecka
				} else {
					prevWords = prevWords + key;
					this.view.print(prevWords);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		
		if(spaceHold && key == ' ') {
			spaceHold = false;
			System.out.println("RELEASE");
		}
	}

}
