package sample.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sample.entities.Bucatar;
import sample.entities.Curs;
import sample.entities.Cursant;
import sample.entities.User;
import sample.exceptions.CouldNotWriteUsersException;
import sample.exceptions.UsernameAlreadyExistsException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.FileUtils;


public class UserService {
    private static List<User> users;
    private static final Path USERS_PATH = FileSystemService.getPathToFile("config", "users.json");

    public static void loadUsersFromFile() throws IOException {
        System.out.println("UserService->loadUsersFromFile()");
        if (!Files.exists(USERS_PATH)) {
            System.out.println("UserService->loadUsersFromFile()->!Files.exists(USERS_PATH)");
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("users.json"), USERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {});
    }

    public static void addCursant(String firstName, String lastName, String email, String phone, String username, String password, String role) throws UsernameAlreadyExistsException {
        System.out.println("UserService->addUser("+firstName+", "+lastName+", "+email+", "+phone+", "+username+", "+password+", "+role+")");
        checkUserDoesNotAlreadyExist(username);
        users.add(new Cursant(firstName, lastName, email, phone,username, encodePassword(username, password), role));
        persistUsers();
    }

    public static void addBucatar(String firstName, String lastName, String email, String phone, String username, String password, String role, Curs curs) throws UsernameAlreadyExistsException {
        System.out.println("UserService->addUser("+firstName+", "+lastName+", "+email+", "+phone+", "+username+", "+password+", "+role+""+curs+")");
        checkUserDoesNotAlreadyExist(username);
        users.add(new Bucatar(firstName, lastName, email, phone,username, encodePassword(username, password), role,curs));
        persistUsers();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        System.out.println("UserService->checkUserDoesNotAlreadyExist()");
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                System.out.println("same username");
                throw new UsernameAlreadyExistsException(username);
            }
        }
    }
    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("users: "+users.size());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }
    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

}

