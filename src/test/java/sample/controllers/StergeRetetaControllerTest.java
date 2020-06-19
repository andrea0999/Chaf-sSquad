package sample.controllers;

import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Reteta;
import sample.services.RetetaService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StergeRetetaControllerTest extends ApplicationTest {
    private static List<Reteta> retete;
    private StergeRetetaController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        retete = RetetaService.getListaRetete();
    }

    @Before
    public void setUp() throws Exception {
        Reteta reteta= new Reteta("Test","faina","stai si astepti","4h");
        controller = new StergeRetetaController();
        controller.setReteta(reteta);
        controller.message= new Text();
        retete.add(reteta);
        RetetaService.setRetete(retete);
        RetetaService.writeJsonRetete("Retete.json");
    }

    @Test
    public void testhandleStergeRetetaActionCode() throws Exception {
        controller.handleStergeReteta();
        assertEquals("Reteta stearsa cu succes!", controller.message.getText());
    }
}
