package sample.service;

import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Reteta;
import sample.services.RetetaService;
import sample.services.ReteteFavoriteService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class ReteteFavoriteServiceTest extends ApplicationTest {

   /* private int ok = 0;
    private static List<Reteta> retete;

    @Before
    public void setUp() throws Exception {
        retete= ReteteFavoriteService.getReteteFavorite();
    }

    @Test
    public void testAdaugaReteta() throws Exception {
        int before= retete.size();
        RetetaService.addReteta("RetetaTest","ingredienteTest","etapeTest","timpTest");
        ok=1;
        retete=RetetaService.getListaRetete();
        assertNotNull(retete);
        assertEquals(before+1, retete.size());
    }*/

}
