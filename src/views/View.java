package views;

import java.awt.Dimension;

import javax.swing.JFrame;

import models.Model;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.SystemColor;

//cale GUI
//pobiera dane z modelu do wyswietlania danych na ekranie
///tylko czyta z modelu - nic nie moze w nim zapisac
public class View extends JFrame{
	private static final long serialVersionUID = 1494887065721121898L;

	private Model model;
	
	private JTextArea textArea;
	
	public View(Model model) {
		this.model = model;
		
		
		getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setLineWrap(true);
		textArea.setRows(5);
		textArea.setBounds(10, 11, 359, 100);
		getContentPane().add(textArea);
		
	//	System.out.println(textArea.getComponentListeners().toString());
		
	//	System.out.println(textArea.getKeyListeners());
		
	//	textArea.getKeyListeners().clone(); {
			
	//	}
		
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.setBounds(379, 122, 95, 23);
		getContentPane().add(btnClear);
		
		JToggleButton tglbtnDictionary = new JToggleButton("Dictionary");
		tglbtnDictionary.setEnabled(false);
		tglbtnDictionary.setBounds(379, 147, 95, 23);
		getContentPane().add(tglbtnDictionary);
		
		JTextArea txtrABC = new JTextArea();
		txtrABC.setBackground(SystemColor.control);
		txtrABC.setColumns(1);
		txtrABC.setEnabled(false);
		txtrABC.setEditable(false);
		txtrABC.setFont(new Font("Monospaced", Font.BOLD, 11));
		txtrABC.setText("a\r\nb\r\nc\r\nd\r\ne\r\nf");
		txtrABC.setBounds(379, 11, 11, 100);
		getContentPane().add(txtrABC);
		
		JTextArea txtrGHI = new JTextArea();
		txtrGHI.setBackground(SystemColor.control);
		txtrGHI.setColumns(1);
		txtrGHI.setEnabled(false);
		txtrGHI.setEditable(false);
		txtrGHI.setText("g\r\nh\r\ni\r\nj\r\nk\r\nl");
		txtrGHI.setFont(new Font("Monospaced", Font.BOLD, 11));
		txtrGHI.setBounds(400, 11, 11, 100);
		getContentPane().add(txtrGHI);
		
		JTextArea txtrMNO = new JTextArea();
		txtrMNO.setBackground(SystemColor.control);
		txtrMNO.setColumns(1);
		txtrMNO.setEnabled(false);
		txtrMNO.setEditable(false);
		txtrMNO.setText("m\r\nn\r\no\r\np\r\nr\r\ns");
		txtrMNO.setFont(new Font("Monospaced", Font.BOLD, 11));
		txtrMNO.setBounds(421, 11, 11, 100);
		getContentPane().add(txtrMNO);
		
		JTextArea txtrTUW = new JTextArea();
		txtrTUW.setBackground(SystemColor.control);
		txtrTUW.setColumns(1);
		txtrTUW.setEnabled(false);
		txtrTUW.setEditable(false);
		txtrTUW.setText("t\r\nu\r\nw\r\nx\r\ny\r\nz");
		txtrTUW.setFont(new Font("Monospaced", Font.BOLD, 11));
		txtrTUW.setBounds(442, 11, 11, 100);
		getContentPane().add(txtrTUW);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBackground(SystemColor.control);
		textArea_4.setColumns(1);
		textArea_4.setEnabled(false);
		textArea_4.setEditable(false);
		textArea_4.setText(" \r\n.\r\n-");
		textArea_4.setFont(new Font("Monospaced", Font.BOLD, 11));
		textArea_4.setBounds(463, 11, 11, 100);
		getContentPane().add(textArea_4);
		
		setPreferredSize(new Dimension(500, 500));
		
		init();
	}
	
	public void init() {
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void print(int finger, int index) {
		print(model.getKey(finger, index));
		textArea.setText(textArea.getText() + model.getKey(finger, index));
	}	
	
	public void print(char x) {
		System.out.print(x);	
	}
	
	public void print(String x) {
		System.out.println(x);	
	}
	
	public void print(long x) {
		System.out.print(x);
	}
	
	public void print(boolean x) {
		System.out.print(x);	
	}
}
