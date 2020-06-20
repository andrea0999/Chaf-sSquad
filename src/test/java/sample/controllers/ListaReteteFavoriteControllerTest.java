package sample.controllers;

import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Reteta;
import sample.entities.RetetaFavorita;
import sample.services.RetetaService;
import sample.services.ReteteFavoriteService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListaReteteFavoriteControllerTest extends ApplicationTest {
    private ListaReteteFavoriteController controller;
    private static List<String> reteteFav;

    @BeforeClass
    public static void setupClass() throws Exception {
        reteteFav = ReteteFavoriteService.getReteteFavorite(ListaReteteCursantiController.getUsernameLogin());
    }

    @Before
    public void setUp() throws Exception {
        reteteFav = ReteteFavoriteService.getReteteFavorite(ListaReteteCursantiController.getUsernameLogin());

        controller = new ListaReteteFavoriteController();
        controller.retetaFavoritaTable = new TableView();
        controller.message = new Text();
    }

    @Test
    public void testSelectareRetetaFavoritaPentruVizualizare() throws Exception {
        controller. handleVizualizarePaginaRetetaFavorita();
        assertEquals("Va rugam selectati o reteta pentru vizualizare!", controller.message.getText());
    }
}
