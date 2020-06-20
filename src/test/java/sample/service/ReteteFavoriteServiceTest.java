package sample.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.RetetaFavorita;
import sample.exceptions.RetetaDejaFavoritaException;
import sample.services.ReteteFavoriteService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class ReteteFavoriteServiceTest extends ApplicationTest {

    private int ok = 0;
    private static List<RetetaFavorita> retete;

    @Before
    public void setUp() throws Exception {
        retete= ReteteFavoriteService.getReteteFavorite();
    }

    @Test
    public void testAdaugaRetetaLaFavorite() throws Exception {
        int before= retete.size();
        System.out.println("hei "+retete);
        ReteteFavoriteService.adaugaRetetaFavorita("test","Tiramisu");
        ok=1;
        retete= ReteteFavoriteService.getReteteFavorite();
        assertNotNull(retete);
        assertEquals(before+1, retete.size());
    }


    @Test(expected = RetetaDejaFavoritaException.class)
    public void testAdaugaRetetaFavDejaExistenta() throws Exception {
        int before= retete.size();
        ReteteFavoriteService.adaugaRetetaFavorita("test","Tiramisu");
        ok = 1;
        assertEquals(before+1,ReteteFavoriteService.getReteteFavorite().size());
        ReteteFavoriteService.adaugaRetetaFavorita("test","Tiramisu");
    }

    @After
    public void resetListaReteteFavorite() throws Exception {
        System.out.println("ReteteFavoriteServiceTest resetListaReteteFavorite()");
        while(ok!=0) {
            List<RetetaFavorita> retetar = ReteteFavoriteService.getReteteFavorite();
            int k = 1;
            for (RetetaFavorita r : retetar) {
                if (k == retetar.size()) {
                    retetar.remove(r);
                    break;
                }
                k++;
            }
            ok--;
            ReteteFavoriteService.setReteteFavorite(retetar);
            ReteteFavoriteService.writeJsonReteteFavorite("ReteteFavorite.json");
        }
    }

}
