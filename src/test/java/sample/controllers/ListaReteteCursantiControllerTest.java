package sample.controllers;

import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.*;
import sample.services.RetetaService;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListaReteteCursantiControllerTest  extends ApplicationTest {
    private ListaReteteCursantiController controller;
    private static List<Reteta> retete;

    @BeforeClass
    public static void setupClass() throws Exception {
        retete = RetetaService.getListaRetete();
    }

    @Before
    public void setUp() throws Exception {
        retete = RetetaService.getListaRetete();

        controller = new ListaReteteCursantiController();
        controller.retetaTable = new TableView();
        controller.message = new Text();
    }

    @Test
    public void testSelectareRetetaPentruVizualizare() throws Exception {
        controller. handleVizualizarePaginaReteta();
        assertEquals("Va rugam selectati o reteta pentru vizualizare", controller.message.getText());
    }

    @Test
    public void testSelectareRetetaPentruFavorit() throws Exception {
        controller. handleAdaugaRetetaFavorita();
        assertEquals("Va rugam selectati o reteta pentru a o adauga la favorite!", controller.message.getText());
    }

    /*@Test
    public void testAdaugaRetetaLaFavoriteSucces() throws Exception {
        ListaReteteCursantiController.setUsername("bereBoss");
        controller.retetaTable.setSelectionModel();
        controller.handleAdaugaRetetaFavorita();
        int before= ReteteFavoriteService.getReteteFavorite(ListaReteteCursantiController.getUsernameLogin()).size();
        assertEquals(before+1, ReteteFavoriteService.getReteteFavorite(ListaReteteCursantiController.getUsernameLogin()).size());
        assertEquals("Reteta adaugata cu succes!", controller.message.getText());
        resetReteteFavorite();
    }
    @Test
    public void testAddSameRetetaFavTwice() throws Exception {
        controller.handleAdaugaRetetaFavorita();
        controller.handleAdaugaRetetaFavorita();
        assertEquals("Reteta %s este deja printre favoritele tale!", controller.message.getText());
    }*/



}
