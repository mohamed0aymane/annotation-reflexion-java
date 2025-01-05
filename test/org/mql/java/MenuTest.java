package org.mql.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mql.java.annotations.Controller;
import org.mql.java.annotations.RequestMapping;
import org.mql.java.ennumeration.Separator;
import org.mql.java.reflection.ui.Menu;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    void testAddMenuWithStrings() {
        String[][] menuData = {
                {"File", "Open", "Save", "Exit"},
                {"Edit", "Cut", "Copy", "Paste"}
        };

        menu = new Menu(menuData);

        assertEquals(2, menu.getMenuCount(), "Le menu devrait contenir 2 catégories");
        assertEquals("File", menu.getMenu(0).getText(), "Le premier menu devrait s'appeler 'File'");
        assertEquals("Edit", menu.getMenu(1).getText(), "Le deuxième menu devrait s'appeler 'Edit'");
    }

    @Test
    void testAddMenuWithController() {
        TestController controller = new TestController();
        menu.add(controller);

        assertEquals(1, menu.getMenuCount(), "Le menu devrait contenir 1 catégorie");
        JMenu controllerMenu = menu.getMenu(0);
        assertEquals("Test Controller", controllerMenu.getText(), "Le menu devrait être nommé 'Test Controller'");
        assertEquals(2, controllerMenu.getItemCount(), "Le menu devrait contenir 2 éléments");

        JMenuItem firstItem = controllerMenu.getItem(0);
        assertEquals("First Action", firstItem.getText(), "Le premier élément devrait s'appeler 'First Action'");
        assertNotNull(firstItem.getActionListeners(), "Le premier élément devrait avoir un ActionListener");

        JMenuItem secondItem = controllerMenu.getItem(1);
        assertEquals("Second Action", secondItem.getText(), "Le deuxième élément devrait s'appeler 'Second Action'");
        assertNotNull(secondItem.getActionListeners(), "Le deuxième élément devrait avoir un ActionListener");
    }

    @Test
    void testAddMenuWithSeparator() {
        SeparatorTestController controller = new SeparatorTestController();
        menu.add(controller);

        JMenu controllerMenu = menu.getMenu(0);
        assertEquals(3, controllerMenu.getItemCount(), "Le menu devrait contenir 3 éléments, avec séparateurs inclus");

        assertTrue(controllerMenu.getItem(0).getText().contains("Action A"), "Le premier élément devrait être 'Action A'");
        assertTrue(controllerMenu.getItem(2).getText().contains("Action C"), "Le troisième élément devrait être 'Action C'");
    }

    @Controller("Test Controller")
    private static class TestController {
        @RequestMapping(name = "First Action", key = 1)
        public void firstAction() {
            System.out.println("First Action executed");
        }

        @RequestMapping(name = "Second Action", key = 2)
        public void secondAction() {
            System.out.println("Second Action executed");
        }
    }

    @Controller("Separator Test")
    private static class SeparatorTestController {
        @RequestMapping(name = "Action A", key = 1, separator = Separator.AFTER)
        public void actionA() {
            System.out.println("Action A executed");
        }

        @RequestMapping(name = "Action C", key = 3, separator = Separator.BEFORE)
        public void actionC() {
            System.out.println("Action C executed");
        }
    }
}
