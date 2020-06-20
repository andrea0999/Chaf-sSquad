package sample.controllers;

import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Cursant;
import sample.entities.Reteta;
import sample.services.RetetaService;
import sample.services.UserService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListaReteteControllerTest extends ApplicationTest {
    private ListaReteteController controller;
    private static List<Reteta> retete;

    @BeforeClass
    public static void setupClass() throws Exception {
        retete = RetetaService.getListaRetete();
    }

    @Before
    public void setUp() throws Exception {
        retete = RetetaService.getListaRetete();

        controller = new ListaReteteController();
        controller.retetaTable = new TableView();
        controller.message = new Text();
    }

    @Test
    public void testSelectareRetetaPentruVizualizare() throws Exception {
        controller.handleVizualizarePaginaReteta();
        assertEquals("Va rugam selectati o reteta pentru vizualizare", controller.message.getText());
    }

    @Test
    public void testSelectareRetetaPentruEditare() throws Exception {
        controller.handleEditeazaReteta();
        assertEquals("Va rugam selectati o reteta pentru editare", controller.message.getText());
    }

    @Test
    public void testSelectareRetetaPentruStergere() throws Exception {
        controller.handleStergeReteta();
        assertEquals("Va rugam selectati o reteta pentru stergere", controller.message.getText());
    }

}
