package sample.controllers;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Cursant;
import sample.entities.StatisticaNote;
import sample.services.StatisticaNotaService;
import sample.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdaugaNotaControllerTest extends ApplicationTest {
    public static final String TEST_ADAUGA_NOTA = "5.5";
    public static final String TEST_username = "bereBoss";

    private AdaugaNotaController controller;



    @Before
    public void setUp() throws Exception {
        controller = new AdaugaNotaController();
        controller.username = new Text();
        controller.notaAdaugata = new TextField();
        controller.message = new Text();

        controller.notaAdaugata.setText(TEST_ADAUGA_NOTA);

        AdaugaNotaController.setCursant(UserService.getCursantByUsername(TEST_username));
    }

    @Test
    public void testNotaAdaugataCuSucces() throws Exception {
        int before = StatisticaNotaService.getNoteCursant(TEST_username).size();
        controller.handleAdaugaNota();
        assertEquals(before+1, StatisticaNotaService.getNoteCursant(TEST_username).size());
        assertEquals("Nota a fost adaugata cu succes!", controller.message.getText());
        resetNote();
    }
    @Test
    public void testIntroduNotaDinInterval() throws Exception {
        controller.notaAdaugata.setText("11");
        controller.handleAdaugaNota();
        assertEquals("Va rugam introduceti o nota in intervalul 1-10!", controller.message.getText());
    }
    @Test
    public void testFormatNotaGresit() throws Exception {
        controller.notaAdaugata.setText("ana");
        controller.handleAdaugaNota();
        assertEquals("Va rugam introduceti o nota!", controller.message.getText());
    }
    @Test
    public void testNumarMaximNoteAtins() throws Exception {
        ArrayList<Double> note = new ArrayList<>();
        note.add(new Double(6));
        note.add(new Double(8.5));
        note.add(new Double(9));
        note.add(new Double(10));
        note.add(new Double(3.5));
        note.add(new Double(6));
        StatisticaNote st = new StatisticaNote("userTest",note);

       List<StatisticaNote> statistica = StatisticaNotaService.getListaStatistica();
        statistica.add(st);
        StatisticaNotaService.setStatistica(statistica);
        StatisticaNotaService.writeJsonStatisticaNote("StatisticaNote.json");

        AdaugaNotaController.setCursant(new Cursant("PrenumeTest","Test","test@yahoo.com","048563258","userTest",UserService.encodePassword("userTest","1234"),"Cursant"));
        controller.notaAdaugata.setText("9");
        controller.handleAdaugaNota();
        assertEquals("Numarul maxim de note pentru userul userTest este atins. Nu se mai pot introduce alte note.", controller.message.getText());

        resetStatistica();
    }

    public void resetNote() throws Exception {
        List<StatisticaNote> statistica = StatisticaNotaService.getListaStatistica();
        int k = 1;
        for (StatisticaNote st : statistica) {
            if (st.getUsername().equals(TEST_username)) {
                for (Double nota : st.getNote()) {
                    if (k == st.getNote().size()) {
                        st.getNote().remove(nota);
                        break;
                    }
                    k++;
                }
            }
        }
        StatisticaNotaService.setStatistica(statistica);
        StatisticaNotaService.writeJsonStatisticaNote("StatisticaNote.json");

    }
    public void resetStatistica() throws Exception {
        List<StatisticaNote> statistica = StatisticaNotaService.getListaStatistica();
        int k = 1;
        for (StatisticaNote st : statistica) {
            if (k == statistica.size()) {
                statistica.remove(st);
                break;
            }
            k++;
        }
        StatisticaNotaService.setStatistica(statistica);
        StatisticaNotaService.writeJsonStatisticaNote("StatisticaNote.json");
    }

}
