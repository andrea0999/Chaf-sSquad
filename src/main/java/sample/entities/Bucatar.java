package sample.entities;

public class Bucatar extends User{

    private Curs curs;

    public Bucatar(String firstName, String lastName, String email, String phone, String username, String password, String role, Curs curs) {
        super(firstName, lastName, email, phone, username, password, role);
        this.curs= curs;
    }

    public Curs getCurs() {
        return curs;
    }

    public void setCurs(Curs curs) {
        this.curs = curs;
    }
}
