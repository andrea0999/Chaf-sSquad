package sample.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sample.entities.Cursant;
import sample.entities.User;
import sample.exceptions.CouldNotWriteUsersException;
import sample.exceptions.UsernameAlreadyExistsException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;


public class UserService {
    private static List<User> users;
    private File file = this.getFileFromResources("users.json");

    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
    public void loadUsersFromFile() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        users = objectMapper.readValue(file, new TypeReference<List<User>>() {});
    }

    public  void addUser(String firstName, String lastName, String email, String phone, String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        if(role.equals("Cursant"))
            users.add(new Cursant(firstName, lastName, email, phone,username, encodePassword(username, password), role));
        persistUsers();
    }
    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : users) {
            if (Objects.equals(username, user.getUsername())) {
                throw new UsernameAlreadyExistsException(username);
            }
        }
    }

    private void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("users: "+users.size());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
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

