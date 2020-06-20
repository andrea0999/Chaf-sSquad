package sample.exceptions;

public class ContCursantInactivException extends Exception {
    private String username;

    public ContCursantInactivException(String username) {
        super(String.format("Contul cu numele de utilizator %s este inactiv!", username));
        this.username = username;
    }
}
