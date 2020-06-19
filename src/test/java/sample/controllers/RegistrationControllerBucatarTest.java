package sample.controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.services.FileSystemService;
import sample.services.UserService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RegistrationControllerBucatarTest extends ApplicationTest {
    public static final String TEST_USER = "andrea";
    public static final String TEST_PASSWORD = "1234";
    private RegistrationControllerBucatar controller;
/*
    @BeforeClass
    public static void setupClass() throws Exception {
        //FileSystemService.APPLICATION_FOLDER = ".test-registrationBucatar-example";
        //FileSystemService.initApplicationHomeDirIfNeeded();
        UserService.getListaUsers();
    }

    @Before
    public void setUp() throws Exception {
       // FileUtils.cleanDirectory("Users.json");
        UserService.getListaUsers();

        controller = new RegistrationControllerBucatar();
        controller.username= new TextField();
        controller.password = new PasswordField();
        //controller.role = new ChoiceBox();
        controller.registrationMessage = new Text();

        controller.password.setText(TEST_PASSWORD);
        controller.username.setText(TEST_USER);
    }

    @Test
    public void testHandleAddUserActionCode() throws Exception {
        int before = UserService.getListaUsers().size();
        controller.handleRegisterBucatar();
        assertEquals(before+1, UserService.getListaUsers().size());
        assertEquals("Account created successfully!", controller.registrationMessage.getText());
    }

    @Test
    public void testAddSameUserTwice() throws IOException {
        controller.handleRegisterBucatar();
        controller.handleRegisterBucatar();
        assertEquals("Un cont cu numele de utilizator " + TEST_USER + " deja exista!", controller.registrationMessage.getText());
    } */
}
