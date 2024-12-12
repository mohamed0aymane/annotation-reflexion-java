package org.mql.java.reflection.ui;

import javax.swing.JFrame;

import org.mql.java.actions.EditAction;
import org.mql.java.actions.FileAction;

public class ExamplesAnnotations extends JFrame {


	private static final long serialVersionUID = 1L;

	public ExamplesAnnotations() {
		exp02();
		setSize(500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	void exp01() {
		//une matrice de java c'est tableau de tableau et chaque element d'un tableau peut avoir n'importe 	quelle element
				String[][] m= {
						{"File","New","Open","Save","SaveAs","Exit"},
						{"Edit","Copy","Cut","Pase"},
						
				};
				Menu menu =new Menu(m);
				setJMenuBar(menu);
	}
	void exp02() {
		Menu menu=new Menu();
		menu.add(new FileAction());
		menu.add(new EditAction());
		setJMenuBar(menu);
	}
	
	
	public static void main(String[] args) {
		new ExamplesAnnotations();

	}

}
