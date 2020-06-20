package sample.controllers;



import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Reteta;
import sample.services.RetetaService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddControllerTest extends ApplicationTest {
    public static final String TEST_TITLU = "Kinder";
    public static final String TEST_INGREDIENTE= "ciocolata,oua";
    public static final String TEST_ETAPE = "Se coace blatul; Se face crema";
    public static final String TEST_TIMP = "3.5";

    private AddController controller;
    private static List<Reteta> retete;


    @BeforeClass
    public static void setupClass() throws Exception {
        retete = RetetaService.getListaRetete();
    }
    @Before
    public void setUp() throws Exception {
        retete = RetetaService.getListaRetete();

        controller = new AddController();
        controller.titluField = new TextField();
        controller.ingredienteField = new TextField();
        controller.etapeCulinareField = new TextField();
        controller.timpPrepareField = new TextField();
        controller.message = new Text();

        controller.titluField.setText(TEST_TITLU);
        controller.ingredienteField.setText(TEST_INGREDIENTE);
        controller.etapeCulinareField.setText(TEST_ETAPE);
        controller.timpPrepareField.setText(TEST_TIMP);

    }

    @Test
    public void testRetetaAdaugataCuSucces() throws Exception {
        int before =retete.size();
        controller.handleAdaugaReteteButtonAction();
        assertEquals(before+1, RetetaService.getListaRetete().size());
        assertEquals("Reteta adaugata cu succes!", controller.message.getText());
    }
    @Test
    public void testAddSameRetetaTwice() throws Exception {
        controller.handleAdaugaReteteButtonAction();
        controller.handleAdaugaReteteButtonAction();
        assertEquals("Reteta cu numele " + TEST_TITLU + " exista!", controller.message.getText());
    }

    @After
    public void resetRetete() throws Exception {
        List<Reteta> retete = RetetaService.getListaRetete();
        int k = 1;
        for (Reteta reteta : retete) {
            if (k == retete.size()) {
                retete.remove(reteta);
                break;
            }
            k++;
        }
        RetetaService.setRetete(retete);
        RetetaService.writeJsonRetete("Retete.json");
    }
}
