package sample.controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Cursant;
import sample.entities.User;
import sample.services.UserService;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SchimbaParolaControllerTest extends ApplicationTest {
    public static final String TEST_PAROLA_VECHE = "1234";
    public static final String TEST_PAROLA_NOUA = "0000";
    public static final String TEST_PAROLA_NOUA_CONFIRMARE = "0000";
    private static List<User> users;
    private SchimbaParolaController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        users= UserService.getListaUsers();
    }

    @Before
    public void setUp() throws Exception {
        Cursant cursant= new Cursant("Andrei","Pascal","andrei@yahoo.com","072356489","andrei",UserService.encodePassword("andrei","1234"),"Cursant",1);
        controller = new SchimbaParolaController();
        controller.setUsername(cursant.getUsername());
        users.add(cursant);
        UserService.setUsers(users);
        UserService.writeJsonUsers("Users.json");

        controller.message= new Text();
        controller.parolaVecheField = new PasswordField();
        controller.parolaNouaField = new PasswordField();
        controller.parolaNouaConfirmareField = new PasswordField();
        controller.parolaVecheField.setText(TEST_PAROLA_VECHE);
        controller.parolaNouaField.setText(TEST_PAROLA_NOUA);
        controller.parolaNouaConfirmareField.setText(TEST_PAROLA_NOUA_CONFIRMARE);
    }

    @Test(expected= IllegalStateException.class)
    public void testHandleSchimbaParolaActionCodeSucces() throws Exception {
        controller.handleSchimbaParolaAction();
        assertEquals("Parola schimbata cu succes!", controller.message.getText());
    }

    @Test
    public void testParolaVecheIncorecta() throws Exception {
        controller.parolaVecheField.setText("incorect");
        controller.handleSchimbaParolaAction();
        assertEquals("Parola actuala este incorecta. Va rugam reincercati!", controller.message.getText());
    }

    @Test
    public void testParolaNouaNeconfirmata() throws Exception {
        controller.parolaNouaField.setText("test");
        controller.parolaNouaConfirmareField.setText("non-test");
        controller.handleSchimbaParolaAction();
        assertEquals("Parola noua nu corespunde cu confirmarea ei. Va rugam reincercati!", controller.message.getText());
    }

    @Test
    public void testParolaNouaFolositaDeja() throws Exception {
        controller.parolaVecheField.setText("1234");
        controller.parolaNouaField.setText("1234");
        controller.parolaNouaConfirmareField.setText("1234");
        controller.handleSchimbaParolaAction();
        assertEquals("Parola noua este o parola pe care ati mai folosit-o. Va rugam introduceti alta!", controller.message.getText());
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
