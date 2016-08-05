package hello;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Hello extends JApplet{

	private static JTextField t = new JTextField(15);
	private static JTextField mod =new JTextField(15);
	private static JButton calc1 = new JButton("Calculation 1");
	private static JButton calc2 = new JButton("Calculation 2");
	
	private BusinessLogic bl = new BusinessLogic(2);
	
	public static int getValue(JTextField tf){
		try{
			return Integer.parseInt(tf.getText());
		}catch(NumberFormatException e){
			return 0;
		}
	}
	
	class Calc1L implements ActionListener{
		public void actionPerformed(ActionEvent e){
			t.setText(Integer.toString(bl.calculation1(getValue(t))));
		}
	}
	
	class Calc2L implements ActionListener{
		public void actionPerformed(ActionEvent e){
			t.setText(Integer.toString(bl.calculation2(getValue(t))));
		}
	}
	
	//If you want something to happer whenever 
	// a JTextField changes, add this listener:
	class Modl implements DocumentListener{
		public void changedUpdate(DocumentEvent e){
			
		}
		
		public void insertUpdate(DocumentEvent e){
			bl.setModifier(getValue(mod));
		}
		
		public void removeUpdate(DocumentEvent e){
			bl.setModifier(getValue(mod));
		}
	}
	
	public  void init(){
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(t);
		
		calc1.addActionListener(new Calc1L());
		calc2.addActionListener(new Calc2L());
		
		JPanel p1 = new JPanel();
		p1.add(calc1);
		p1.add(calc2);
		cp.add(p1);
		mod.getDocument().addDocumentListener(new Modl());
		
		JPanel p2 = new JPanel();
		p2.add(new JLabel("Modifier"));
		p2.add(mod);
		cp.add(p2);
		
		
		
	}
	public static void main(String[] args){
		System.out.println("hello world");
		
		run(new Hello(), 250, 100);
	}

	private static void run(JApplet applet, int width, int height) {
		// TODO say hello from GUI
		JFrame frame = new JFrame("Hello GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(applet);
		frame.setSize(width, height);
		applet.init();
		applet.start();
		frame.pack();
		frame.setVisible(true);
				
	}
}
