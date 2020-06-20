package sample.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import sample.entities.Curs;
import sample.entities.User;
import sample.exceptions.ContCursantInactivException;
import sample.exceptions.UsernameAlreadyExistsException;
import sample.services.UserService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class UserServiceTest extends ApplicationTest {
    private int ok = 0;
    private static List<User> users;

    @Before
    public void setUp() throws Exception {
        users=UserService.getListaUsers();
    }

    @Test
    public void testAdaugaUnCursant() throws Exception {
        int before= users.size();
        UserService.addCursant("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Cursant");
        ok=1;
        users = UserService.getListaUsers();
        assertNotNull(users);
        assertEquals(before+1, users.size());
    }

    @Test
    public void testAdaugaDoiCursanti() throws Exception {
        int before= users.size();
        UserService.addCursant("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Cursant");
        UserService.addCursant("prenumeTest2", "numeTest2", "test2@yahoo.com","0740065942","test2", "1234","Cursant");
        ok=2;
        users = UserService.getListaUsers();
        assertNotNull(users);
        assertEquals(before+2, users.size());
    }

    @Test
    public void testAdaugaUnBucatar() throws Exception {
        int before= users.size();
        UserService.addBucatar("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Bucatar",new Curs("titluTest","descriereTest",450,15));
        ok=1;
        users = UserService.getListaUsers();
        assertNotNull(users);
        assertEquals(before+1, users.size());
    }

    @Test
    public void testAdaugaDoiBucatari() throws Exception {
        int before= users.size();
        UserService.addBucatar("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Bucatar",new Curs("titluTest","descriereTest",450,15));
        UserService.addBucatar("prenumeTest2", "numeTest2", "test2@yahoo.com","0748265002","test2", "1234","Bucatar",new Curs("titluTest2","descriereTest2",430,10));
        ok=2;
        users = UserService.getListaUsers();
        assertNotNull(users);
        assertEquals(before+2, users.size());
    }

    @Test(expected = UsernameAlreadyExistsException.class)
    public void testAddUserAlreadyExists() throws Exception {
        UserService.addCursant("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Cursant");
        ok = 1;
        assertNotNull(UserService.getListaUsers());
        UserService.checkUserDoesNotAlreadyExist("test");
    }

    @Test
    public void testPasswordEncoding() {
        ok= 0;
        assertNotEquals("testPass1", UserService.encodePassword("username1", "testPass1"));
    }

    @Test
    public void testCheckCredentialeSuccesCursant() throws Exception {
        UserService.addCursant("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Cursant");
        ok = 1;
        assertEquals(true,UserService.checkCredentiale("test","1234","Cursant"));
    }

    @Test(expected= ContCursantInactivException.class)
    public void testCheckCredentialeInactivitateCursant() throws Exception {
        UserService.addCursant("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Cursant");
        UserService.schimbaValabilitateContCursant("test",0);
        ok = 1;
        UserService.checkCredentiale("test","1234","Cursant");
    }

    @Test
    public void testCheckCredentialeSuccesBucatar() throws Exception {
        UserService.addBucatar("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Bucatar",new Curs("titluTest","descriereTest",450,15));
        ok = 1;
        assertEquals(true,UserService.checkCredentiale("test","1234","Bucatar"));
    }

    @Test()
    public void testSchimbaParolaUserSucces() throws Exception {
        UserService.addCursant("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Cursant");
        ok = 1;
        UserService.schimbaParola("test","0000");
        assertEquals(UserService.encodePassword("test","0000"),UserService.getCursantByUsername("test").getPassword());
    }

    @Test()
    public void testSchimbaValabilitateCursantSucces() throws Exception {
        UserService.addCursant("prenumeTest", "numeTest", "test@yahoo.com","0748265942","test", "1234","Cursant");
        ok = 1;
        UserService.schimbaValabilitateContCursant("test",0);
        assertEquals(0,UserService.getCursantByUsername("test").getValabilitate());
    }

    @After
    public void resetListaUsers() throws Exception {
        System.out.println("UserServiceTest resetListaUsers()");
        while(ok!=0) {
            List<User> users = UserService.getListaUsers();
            int k = 1;
            for (User user : users) {
                if (k == users.size()) {
                    users.remove(user);
                    break;
                }
                k++;
            }
            ok--;
            UserService.setUsers(users);
            UserService.writeJsonUsers("Users.json");
        }
    }
}
