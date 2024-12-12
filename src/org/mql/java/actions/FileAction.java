package org.mql.java.actions;

import org.mql.java.annotations.Controller;
import org.mql.java.annotations.RequestMapping;
import org.mql.java.ennumeration.Separator;

@Controller("File")
public class FileAction {

	public FileAction() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(name="newFile",icon="resources/icons/newFile.gif",key=1,separator =Separator.AFTER)
	public String newFile() {
		System.out.println("Create a new File");
		return "new-file.jsp";
	}
	@RequestMapping(name="open",icon="resources/icons/open.gif",key=2,separator =Separator.AFTER)
	public String open() {
		System.out.println("Open a new File");
		return "open.jsp";
	}
	@RequestMapping(name="save",icon="resources/icons/save.gif",key=3)
	public String save() {
		System.out.println("Save a new File");
		return "save.jsp";
	}
	@RequestMapping(name="saveAS",key=4 ,separator =Separator.AFTER)
	public String saveAs() {
		System.out.println("Save as a new File");
		return "save-as.jsp";
	}
	@RequestMapping(name="exit",key=5)
	public String exit() {
		System.out.println("Exit the application");
		return "exit.jsp";
	}

}
