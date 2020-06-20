package sample.controllers;


import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.*;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Cursant;
import sample.entities.User;
import sample.services.UserService;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegistrationControllerCursantTest extends ApplicationTest {
    public static final String TEST_USER = "sonia";
    public static final String TEST_PASSWORD = "1234";
    public static final  String TEST_FIRSTNAME = "Sonia";
    public static final  String TEST_LASTNAME = "Mateiu";
    public static final  String TEST_EMAIL = "sonia@yahoo.com";
    public static final  String TEST_PHONE = "073265489";

    private static List<Cursant> cursanti;
    private RegistrationControllerCursant controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        cursanti = UserService.getListaCursanti();
    }

    @Before
    public void setUp() throws Exception {
        cursanti = UserService.getListaCursanti();

        controller = new RegistrationControllerCursant();
        controller.firstname = new TextField();
        controller.lastname = new TextField();
        controller.email = new TextField();
        controller.phone = new TextField();

        controller.username= new TextField();
        controller.password = new PasswordField();
        //controller.role = new ChoiceBox();
        controller.registrationMessage = new Text();

        controller.password.setText(TEST_PASSWORD);
        controller.username.setText(TEST_USER);
        controller.firstname.setText(TEST_FIRSTNAME);
        controller.lastname.setText(TEST_LASTNAME);
        controller.email.setText(TEST_EMAIL);
        controller.phone.setText(TEST_PHONE);

    }

    @Test
    public void testHandleAddUserActionCode() throws Exception {
        int before =cursanti.size();
        controller.handleRegisterCursant();
        assertEquals(before+1, UserService.getListaCursanti().size());
        assertEquals("Account created successfully!", controller.registrationMessage.getText());
    }

    @Test
    public void testAddSameUserTwice() throws  IOException {
        controller.handleRegisterCursant();
        controller.handleRegisterCursant();
        assertEquals("Un cont cu numele de utilizator " + TEST_USER + " deja exista!", controller.registrationMessage.getText());
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
