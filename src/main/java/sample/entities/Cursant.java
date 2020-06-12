package sample.entities;

public class Cursant extends User {

    private int valabilitate;                        //0-cont dezactivat, 1- cont activ

    public Cursant(String firstName, String lastName, String email, String phone, String username, String password, String role) {
        super(firstName, lastName, email, phone, username, password, role);
        valabilitate = 1;                            //Contul este  activ by default
    }

    public Cursant(String firstName, String lastName, String email, String phone, String username, String password, String role, int valabilitate) {
        super(firstName, lastName, email, phone, username, password, role);
        this.valabilitate = valabilitate;
    }

    public int getValabilitate() { return valabilitate; }

    public void setValabilitate(int valabilitate) { this.valabilitate = valabilitate; }
}
