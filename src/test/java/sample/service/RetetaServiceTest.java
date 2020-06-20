package sample.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sample.entities.Reteta;
import sample.exceptions.RetetaAlreadyExistsException;
import sample.services.RetetaService;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class RetetaServiceTest {
    private int ok = 0;
    private static List<Reteta> retete;

    @Before
    public void setUp() throws Exception {
        retete= RetetaService.getListaRetete();
    }

    @Test
    public void testAdaugaReteta() throws Exception {
        int before= retete.size();
        RetetaService.addReteta("RetetaTest","ingredienteTest","etapeTest","timpTest");
        ok=1;
        retete=RetetaService.getListaRetete();
        assertNotNull(retete);
        assertEquals(before+1, retete.size());
    }
    @Test
    public void testAdaugaDouaRetete() throws Exception {
        int before= retete.size();
        RetetaService.addReteta("RetetaTest","ingredienteTest","etapeTest","timpTest");
        RetetaService.addReteta("RetetaTest2","ingredienteTest2","etapeTest2","timpTest2");
        ok=2;
        retete=RetetaService.getListaRetete();
        assertNotNull(retete);
        assertEquals(before+2, retete.size());
    }

    @Test(expected = RetetaAlreadyExistsException.class)
    public void testAdaugaRetetaDejaExistenta() throws Exception {
        int before= retete.size();
        RetetaService.addReteta("RetetaTest","ingredienteTest","etapeTest","timpTest");
        ok = 1;
        assertEquals(before+1,RetetaService.getListaRetete().size());
        RetetaService.checkRetetaDoesNotAlreadyExist("RetetaTest");
    }

    @Test
    public void testEditeazaReteta() throws Exception {
        RetetaService.addReteta("RetetaTest","ingredienteTest","etapeTest","timpTest");
        ok=1;
        RetetaService.editeazaReteta("RetetaTest","Test","ingrediente","1,2,3","1h");
        retete=RetetaService.getListaRetete();
        assertNotNull(RetetaService.getRetetaByName("Test"));
        assertEquals("ingrediente",RetetaService.getRetetaByName("Test").getIngrediente());
    }

    @Test
    public void testStergeReteta() throws Exception {
        int before= retete.size();
        RetetaService.addReteta("RetetaTest","ingredienteTest","etapeTest","timpTest");
        RetetaService.stergeReteta("RetetaTest");
        retete=RetetaService.getListaRetete();
        ok=0;
        assertEquals(before,retete.size());
    }


    @After
    public void resetListaRetete() throws Exception {
        System.out.println("RetetaServiceTest resetListaRetete()");
        while(ok!=0) {
            List<Reteta> retetar = RetetaService.getListaRetete();
            int k = 1;
            for (Reteta r : retetar) {
                if (k == retetar.size()) {
                    retetar.remove(r);
                    break;
                }
                k++;
            }
            ok--;
            RetetaService.setRetete(retetar);
            RetetaService.writeJsonRetete("Retete.json");
        }
    }

}
