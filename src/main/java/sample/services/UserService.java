package sample.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.entities.*;
import sample.exceptions.ContCursantInactivException;
import sample.exceptions.UsernameAlreadyExistsException;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class UserService {

    private static List<User> users = new ArrayList<>();
    private static  boolean dejaParcurs = false;


    public static Object readJsonUsers(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    public static void parcurgereListaUsers() throws Exception {
        if(!dejaParcurs){
            JSONArray listaUsers = (JSONArray) readJsonUsers("Users.json");
            dejaParcurs=true;
            Iterator<JSONObject> iterator = listaUsers.iterator();
            while (iterator.hasNext()) {
                User u;
                JSONObject userJson = iterator.next();
                System.out.println("UserService parcurgereListaUsers() role="+userJson.get("role").toString());
                if(userJson.get("role").toString().equals("Cursant")) {
                     u = new Cursant(userJson.get("firstname").toString(), userJson.get("lastname").toString(),userJson.get("email").toString(), userJson.get("phone").toString(),userJson.get("username").toString(), userJson.get("password").toString(),userJson.get("role").toString(),Integer.parseInt(userJson.get("valabilitate").toString()));
                } else {
                    JSONObject cursJson = (JSONObject) userJson.get("Curs");
                    Curs c = new Curs(cursJson.get("titlu").toString(), cursJson.get("descriere").toString(), Double.parseDouble(cursJson.get("cost").toString()), Integer.parseInt(cursJson.get("nrParticipanti").toString()));
                    u = new Bucatar(userJson.get("firstname").toString(), userJson.get("lastname").toString(), userJson.get("email").toString(), userJson.get("phone").toString(), userJson.get("username").toString(), userJson.get("password").toString(), userJson.get("role").toString(),c);
               }
                users.add(u);
            }
        }
    }
    public static void writeJsonUsers(String filename) throws Exception {
        JSONArray listaUsers = new JSONArray();

        for(User user : users){
            JSONObject sampleObject = new JSONObject();
            if(user.getRole().equals("Cursant")){
                Cursant c =(Cursant) user;
                sampleObject.put("firstname", c.getFirstName());
                sampleObject.put("lastname", c.getLastName());
                sampleObject.put("email", c.getEmail());
                sampleObject.put("phone", c.getPhone());
                sampleObject.put("username", c.getUsername());
                sampleObject.put("password", c.getPassword());
                sampleObject.put("role", c.getRole());
                sampleObject.put("valabilitate", c.getValabilitate());
                listaUsers.add(sampleObject);
            }
            else{
                Bucatar b =(Bucatar) user;
                sampleObject.put("firstname", b.getFirstName());
                sampleObject.put("lastname", b.getLastName());
                sampleObject.put("email", b.getEmail());
                sampleObject.put("phone", b.getPhone());
                sampleObject.put("username", b.getUsername());
                sampleObject.put("password", b.getPassword());
                sampleObject.put("role", b.getRole());
                JSONObject cursObject = new JSONObject();
                cursObject.put("titlu",b.getCurs().getTitlu());
                cursObject.put("descriere",b.getCurs().getDescriere());
                cursObject.put("cost",b.getCurs().getCost());
                cursObject.put("nrParticipanti",b.getCurs().getNrParticipanti());
                sampleObject.put("Curs",cursObject);
                listaUsers.add(sampleObject);
            }
        }

        Files.write(Paths.get(filename), listaUsers.toJSONString().getBytes());
    }

    public static void addCursant(String firstName, String lastName, String email, String phone, String username, String password, String role) throws Exception {
        System.out.println("UserService->addUser("+firstName+", "+lastName+", "+email+", "+phone+", "+username+", "+password+", "+role+")");
        parcurgereListaUsers();
        checkUserDoesNotAlreadyExist(username);
        users.add(new Cursant(firstName, lastName, email, phone,username, encodePassword(username, password), role));
        writeJsonUsers("Users.json");
    }

    public static void addBucatar(String firstName, String lastName, String email, String phone, String username, String password, String role, Curs curs) throws Exception {
        System.out.println("UserService->addUser("+firstName+", "+lastName+", "+email+", "+phone+", "+username+", "+password+", "+role+""+curs+")");
        parcurgereListaUsers();
        checkUserDoesNotAlreadyExist(username);
        users.add(new Bucatar(firstName, lastName, email, phone,username, encodePassword(username, password), role,curs));
        writeJsonUsers("Users.json");
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

    public static String encodePassword(String salt, String password) {
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

    public static boolean checkCredentiale(String username,String password,String role) throws Exception {
        parcurgereListaUsers();
        for (User user : users) {

            if (Objects.equals(username, user.getUsername()) && Objects.equals(encodePassword(username, password), user.getPassword()) && Objects.equals(role, user.getRole())) {
                if(role.equals("Cursant")) {
                    Cursant c = (Cursant) user;
                    if(c.getValabilitate() == 1){
                        System.out.println("UserService checkCredentiale() login acces true");
                        return true;
                    }
                    else
                        throw new ContCursantInactivException(c.getUsername());
                }
                else {
                    System.out.println("UserService checkCredentiale() login acces true");
                    return true;
                }
            }
        }
        return false;
    }

    public static List<User>  getListaUsers() throws Exception {
        System.out.println("UserService->getListaUsers()");
        parcurgereListaUsers();
        return users;

    }

    public static List<Cursant>  getListaCursanti() throws Exception {
        System.out.println("UserService->getListaCursanti()");
        List<Cursant> cursanti = new ArrayList<>();
       parcurgereListaUsers();
        for(User user: users){
            if(user.getRole().equals("Cursant"))
                cursanti.add((Cursant) user);
        }
        return cursanti;
    }

    public static void schimbaParola(String username,String password) throws Exception {
        parcurgereListaUsers();
        for(User user : users){
            if(user.getUsername().equals(username)){
                user.setPassword(encodePassword(username,password));
                writeJsonUsers("Users.json");
            }
        }
    }
   /* public static Bucatar getBucatarByUsername(String username) throws Exception {
        parcurgereListaUsers();
        for(User user: users)
            if(user.getUsername().equals(username))
                return (Bucatar)user;
            return null;
    }*/
    public static Cursant getCursantByUsername(String username) throws Exception {
        parcurgereListaUsers();
        for(User user: users)
            if(user.getUsername().equals(username))
                return (Cursant)user;
        return null;
    }

    public static void schimbaValabilitateContCursant(String username, int valabilitate) throws Exception {
        parcurgereListaUsers();
        for(User user : users){
            if(user.getUsername().equals(username)){
                Cursant c = (Cursant) user;
                c.setValabilitate(valabilitate);
                writeJsonUsers("Users.json");
            }
        }
    }
    public static Bucatar getBucatar() throws Exception {
        parcurgereListaUsers();
        for(User user : users){
            if(user.getRole().equals("Bucatar")){
                Bucatar bucatar= (Bucatar) user;
                return bucatar;
            }
        }
        return null;
    }

    public static int getNrCursanti() throws Exception {
        parcurgereListaUsers();
        int count= 0;
        for(User user : users)
            if(user.getRole().equals("Cursant"))
               count++;
        return count;
    }

    public static void setUsers(List<User> users) {
        UserService.users = users;
    }
}

