package sample.controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Cursant;
import sample.entities.User;
import sample.exceptions.ContCursantInactivException;
import sample.services.UserService;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest extends ApplicationTest {
    public static final String TEST_USER = "amalia";
    public static final String TEST_PASSWORD = "1234";
    public static final String TEST_ROL = "Cursant";


    private static List<User> users;
    private LoginController controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        //FileSystemService.APPLICATION_FOLDER = ".test-registrationBucatar-example";
        //FileSystemService.initApplicationHomeDirIfNeeded();
        users = UserService.getListaUsers();
    }

    @Before
    public void setUp() throws Exception {
        // FileUtils.cleanDirectory("Users.json");

        controller = new LoginController();
        controller.usernameField = new TextField();
        controller.passwordField = new PasswordField();
        controller.roleField = new ChoiceBox();
        controller.loginMessage = new Text();

        controller.passwordField.setText(TEST_PASSWORD);
        controller.usernameField.setText(TEST_USER);
        controller.roleField.setValue(TEST_ROL);


    }

    @Test(expected = Exception.class)
    public void testHandleLoginAccesSuccesfullCursant() throws Exception {
        controller.handleLoginButtonAction();
        System.out.println("testLogin");
        assertEquals("Autentificare cu succes", controller.loginMessage.getText());
    }
    @Test
    public void testHandleLoginAccesFailCursant() throws Exception {
        controller.passwordField.setText("12340");
        controller.handleLoginButtonAction();
        assertEquals("Incorrect login!", controller.loginMessage.getText());
    }

    @Test(expected = Exception.class)
    public void testHandleLoginAccesSuccesfullBucatar() throws Exception {
        controller.usernameField.setText("andrea");
        controller.passwordField.setText("1234");
        controller.roleField.setValue("Bucatar");
        controller.handleLoginButtonAction();
        System.out.println("testLogin");
        assertEquals("Autentificare cu succes", controller.loginMessage.getText());
    }
    @Test
    public void testHandleLoginAccesFailBucatar() throws Exception {
        controller.usernameField.setText("andrea");
        controller.passwordField.setText("12345");
        controller.roleField.setValue("Bucatar");
        controller.handleLoginButtonAction();
        System.out.println("testLogin");
        assertEquals("Incorrect login!", controller.loginMessage.getText());
    }

    @Test
    public void testInactivitateCursant() throws Exception {
        List<User> users = UserService.getListaUsers();
        users.add(new Cursant("Andrei","Pascal","andrei@yahoo.com","072356489","andrei",UserService.encodePassword("andrei","1234"),"Cursant",0));
        UserService.setUsers(users);
        UserService.writeJsonUsers("Users.json");
        controller.usernameField.setText("andrei");
        controller.passwordField.setText("1234");
        controller.roleField.setValue("Cursant");

        controller.handleLoginButtonAction();
        assertEquals("Contul cu numele de utilizator andrei este inactiv!", controller.loginMessage.getText());
        resetListaCursanti();
    }

    @Test(expected = Exception.class)
    public void tesBlocareBotonCursant() throws Exception {
        controller.handleLoginButtonAction();
        assertEquals("Ati ajuns la numarul maxim de incercari; Va rugam asteptati 30 de minute si apoi reincercati", controller.loginMessage.getText());
    }


    public void resetListaCursanti() throws Exception {
        List<User> users = UserService.getListaUsers();
        int k = 1;
        for (User user : users) {
            if (k == users.size()) {
                users.remove(user);
                break;
            }
            k++;
        }
        UserService.setUsers(users);
        UserService.writeJsonUsers("Users.json");
    }

}
