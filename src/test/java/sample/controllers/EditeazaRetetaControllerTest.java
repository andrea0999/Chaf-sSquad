package sample.controllers;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Cursant;
import sample.entities.Reteta;
import sample.entities.StatisticaNote;
import sample.entities.User;
import sample.services.RetetaService;
import sample.services.StatisticaNotaService;
import sample.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EditeazaRetetaControllerTest extends ApplicationTest {
    public static final String TEST_NUME = "Tiramisu";
    public static final String TEST_Ingrediente = "ciocolata";
    public static final String TEST_Etape = "prepararea cremei";
    public static final String TEST_Timp = "3ore jumate";

    private EditeazaRetetaController controller;
    private Reteta retetaAux,retetaVeche;

    @Before
    public void setUp() throws Exception {
        controller = new EditeazaRetetaController();
        controller.nume = new TextField();
        controller.ingrediente = new TextField();
        controller.etapeCulinare = new TextField();
        controller.timpDePreparare = new TextField();

        controller.message = new Text();

        controller.nume.setText(TEST_NUME);
        controller.ingrediente.setText(TEST_Ingrediente);
        controller.etapeCulinare.setText(TEST_Etape);
        controller.timpDePreparare.setText(TEST_Timp);

        controller.setReteta(RetetaService.getRetetaByNume(TEST_NUME));
    }

    @Test
    public void testRetetaEditataCuSucces() throws Exception {
        retetaAux = RetetaService.getRetetaByNume(TEST_NUME);
        retetaVeche = new Reteta(retetaAux.getNume(),retetaAux.getIngrediente(),retetaAux.getEtapeCulinare(),retetaAux.getTimpDePreparare());
        controller.handleEditeazaReteta();
        assertEquals("Reteta editata cu succes!", controller.message.getText());
        resetRetete();
    }
    @Test
    public void testRetetaEditataExistenta() throws Exception {
        controller.nume.setText("Kinder");
        List<Reteta> retete = RetetaService.getListaRetete();
        retete.add(new Reteta("Kinder","biscuiti,oua,ciocolata","preparare compozitie","2ore jumate"));
        RetetaService.setRetete(retete);
        RetetaService.writeJsonRetete("Retete.json");

        controller.handleEditeazaReteta();
        assertEquals("Aceasta reteta exista deja in lista de retete", controller.message.getText());
        resetListaRetete();
    }

    public void resetRetete() throws Exception {
        List<Reteta> retete = RetetaService.getListaRetete();
        for (Reteta r : retete) {
            if (r.getNume().equals(TEST_NUME)) {
                retete.remove(r);
                retete.add(retetaVeche);
                break;
            }
        }
        RetetaService.setRetete(retete);
        RetetaService.writeJsonRetete("Retete.json");
    }

    public void resetListaRetete() throws Exception {
        List<Reteta> retete = RetetaService.getListaRetete();
        int k=1;
        for(Reteta r : retete){
            if(k==retete.size()) {
                retete.remove(r);
                break;
            }
            k++;
        }
        RetetaService.setRetete(retete);
        RetetaService.writeJsonRetete("Retete.json");
    }

}
