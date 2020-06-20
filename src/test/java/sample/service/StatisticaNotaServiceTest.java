package sample.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.StatisticaNote;
import sample.services.StatisticaNotaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class StatisticaNotaServiceTest extends ApplicationTest {
    private static List<StatisticaNote> statistica = new ArrayList<>();
    int ok =0;

    @Before
    public void setUp() throws Exception {
        statistica= StatisticaNotaService.getListaStatistica();
    }
    @Test
    public void CalculMedieSucces() throws Exception {
        ArrayList<Double> note=new ArrayList<>(6);
        note.add(new Double(10));
        note.add(new Double(10));
        note.add(new Double(10));
        note.add(new Double(10));
        note.add(new Double(10));
        note.add(new Double(10));
        statistica.add(new StatisticaNote("test",note));
        ok=1;
        Double medie =StatisticaNotaService.getMedieCursant("test");
        assertEquals("10.0",medie.toString());
    }

    @After
    public void resetStatistica() throws Exception {
        System.out.println("StatisticaNoteServiceTest resetStatistica()");
        while(ok!=0) {
            List<StatisticaNote> statistique = StatisticaNotaService.getListaStatistica();
            int k = 1;
            for (StatisticaNote r : statistique) {
                if (k == statistique.size()) {
                    statistique.remove(r);
                    break;
                }
                k++;
            }
            ok--;
            StatisticaNotaService.setStatistica(statistique);
            StatisticaNotaService.writeJsonStatisticaNote("StatisticaNote.json");
        }
    }
}
