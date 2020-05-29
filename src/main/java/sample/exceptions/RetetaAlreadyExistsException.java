package sample.exceptions;

public class RetetaAlreadyExistsException extends Exception{
    private String nume;

    public RetetaAlreadyExistsException(String nume) {
        super(String.format("Reteta cu numele %s exista!", nume));
        this.nume = nume;
    }

    public String getUsername() {
        return nume;
    }
}
