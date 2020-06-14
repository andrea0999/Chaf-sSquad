package sample.exceptions;

public class NumarMaximDeNoteAtinsException extends Exception {
    private String username;

    public NumarMaximDeNoteAtinsException(String username) {
        super(String.format("Numarul maxim de note pentru userul %s este atins. Nu se mai pot introduce alte note.", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
