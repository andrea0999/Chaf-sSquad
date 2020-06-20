package sample.controllers;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Cursant;
import sample.services.UserService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListaCursantiControllerTest  extends ApplicationTest {

    private ListaCursantiController controller;
    private static List<Cursant> cursanti;

    @BeforeClass
    public static void setupClass() throws Exception {
        cursanti = UserService.getListaCursanti();
    }

    @Before
    public void setUp() throws Exception {
        cursanti = UserService.getListaCursanti();

        controller = new ListaCursantiController();
        controller.cursantTable = new TableView();
        controller.message = new Text();
    }

    @Test
    public void testSelectareCursantPentruVizualizare() throws Exception {
        controller.handleVizualizarePaginaCursant();
        assertEquals("Va rugam selectati un cursant pentru vizualizare", controller.message.getText());
    }

    @Test
    public void testSelectareCursantAdaugaNota() throws Exception {
        controller.handleAdaugaNotaCursant();
        assertEquals("Va rugam selectati un cursant pentru a-i da o nota", controller.message.getText());
    }

    @Test
    public void testSelectareCursantPentruValidare() throws Exception {
        controller.handleSchimbaValabilitateContCursant();
        assertEquals("Va rugam selectati un cursant pentru a-i activa/dezactiva contul", controller.message.getText());
    }
}
