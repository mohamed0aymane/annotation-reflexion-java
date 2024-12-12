package org.mql.java.actions;

import org.mql.java.annotations.Controller;
import org.mql.java.annotations.RequestMapping;
import org.mql.java.ennumeration.Separator;

@Controller("Edit")
public class EditAction {

	public EditAction() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(name="copy",icon="resources/icons/copy.gif",key=1,separator =Separator.AFTER)
	public String copy() {
		System.out.println("Copy");
		return	"copy.jsp";
	}
	@RequestMapping(name="cut",icon="resources/icons/cut.gif",key=2)
	public String cut() {
		System.out.println("Cut");
		return	"cut.jsp";
	}
	@RequestMapping(name="paste",icon="resources/icons/paste.gif",key=3,separator =Separator.BEFORE)
	public String paste() {
		System.out.println("Paste");
		return	"paste.jsp";
	}
}
