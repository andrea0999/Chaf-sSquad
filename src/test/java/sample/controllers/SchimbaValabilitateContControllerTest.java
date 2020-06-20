package sample.controllers;

import javafx.scene.text.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Cursant;
import sample.entities.Reteta;
import sample.entities.User;
import sample.services.RetetaService;
import sample.services.UserService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SchimbaValabilitateContControllerTest  extends ApplicationTest {

    private static List<User> users;
    private SchimbaValabilitateContController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        users= UserService.getListaUsers();
    }

    @Before
    public void setUp() throws Exception {
        Cursant cursant= new Cursant("Andrei","Pascal","andrei@yahoo.com","072356489","andrei",UserService.encodePassword("andrei","1234"),"Cursant",1);
        controller = new SchimbaValabilitateContController();
        controller.setCursant(cursant);
        controller.message= new Text();
        users.add(cursant);
        UserService.setUsers(users);
        UserService.writeJsonUsers("Users.json");
    }

    @Test
    public void testhHandleSchimbaValabilitateaActionCode() throws Exception {
        controller.handleSchimbaValabilitatea();
        Cursant testCursant=UserService.getCursantByUsername("andrei");
        assertEquals(0,testCursant.getValabilitate());
        assertEquals("Contul a fost dezactivat cu succes!", controller.message.getText());
    }
    @After
    public void resetListaCursanti() throws Exception {
        List<User> users = UserService.getListaUsers();
        int k=1;
        for(User user : users){
            if(k==users.size()) {
                users.remove(user);
                break;
            }
            k++;
        }
        UserService.setUsers(users);
        UserService.writeJsonUsers("Users.json");
    }
}
