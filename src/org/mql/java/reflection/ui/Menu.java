package org.mql.java.reflection.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;

import org.mql.java.actions.EditAction;
import org.mql.java.annotations.Controller;
import org.mql.java.annotations.RequestMapping;
import org.mql.java.ennumeration.Separator;


public class Menu extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	private String path="resources/icons/";
	private String type=".gif";

	
	public Menu() {
	}
	
	public Menu(String m [][]) {
		
		for(int i=0;i<m.length;i++) {
			add(m[i]);
		}
	}
	

	public void add(String t[]) {
		
		JMenu m =new JMenu(t[0]);
		add(m); 
		
		for(int i=1;i<t.length;i++) {	
			ImageIcon icon=new ImageIcon(path + t[i] + type);
			JMenuItem item=new JMenuItem(t[i],icon); 
			m.add(item); 
		}
		
	}
	
	

	public void add(Object obj) {
		//Recuperation des informations de la classe 
		Class<?> cls=obj.getClass();
		Controller ctrl=cls.getDeclaredAnnotation(Controller.class);
		JMenu m=new JMenu(ctrl.value());
		add(m);
		
		//Recuperation des methodes annotees
		Method methods[]=cls.getDeclaredMethods();
		//Stocker les informations dans une liste
		ArrayList<Object[]> items = new ArrayList<>();
		
		
		for(Method method:methods) {
			RequestMapping rm=method.getDeclaredAnnotation(RequestMapping.class);
			
			if(rm!=null) {
				String name = rm.name().isEmpty() ? method.getName() : rm.name();
				String icon = rm.icon();
				int key = rm.key();
				Separator separator = rm.separator();
				items.add(new Object[] {name, icon, key, separator, method});
			}
		}
		
		//trier les elements par cle
		//le principe utilise si le cle d'un element est superieure a celle d'un autre
		// on echange leur position dans la liste
		for(int i=0;i<items.size();i++) {
			for(int j=i+1;j<items.size();j++) {
				if((int)items.get(i)[2]> (int)items.get(j)[2]) {//on [2] il reference eu 3 item du liste qui est key
					Object[] temp=items.get(i);
					items.set(i, items.get(j));
					items.set(j, temp);
				}
			}
		}
		
		//Creation des elements du menu
		for (int i = 0; i < items.size(); i++) {
			 String name = (String) items.get(i)[0];
		     String icon = (String) items.get(i)[1];
		     Separator separator = (Separator) items.get(i)[3];
		     Method method = (Method) items.get(i)[4];
			
			
	        if (separator == Separator.BEFORE && i == 0) {
	            m.addSeparator();
	           //Un separateur ajoute avant un element non premier uniquement si le precedent  n'a pas deja un separateur apres lui.
	        } else if (separator == Separator.BEFORE && i > 0 && items.get(i - 1)[3] != Separator.AFTER) {
	            m.addSeparator();
	        }
	        
	        //ajouter l'element du menu
			ImageIcon iconImage = icon.isEmpty() ? null : new ImageIcon(icon);
			JMenuItem menuItem=new JMenuItem(name,iconImage);
			menuItem.addActionListener(new Action(obj,method));
			m.add(menuItem);
			
		
			if(separator==Separator.AFTER) {
				m.addSeparator();
			}			
		}
				
	}
	//avec l'introspection on peut tous recuperer
	private class Action implements ActionListener{
		private Object obj; 
		private Method method; 

		
		public Action(Object obj, Method method) {
			super();
			this.obj = obj;
			this.method = method;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				Object result=method.invoke(obj);
				
				System.out.println("result = "+ result);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		}
		
	}
	

}
