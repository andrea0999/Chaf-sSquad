package sample.entities;

public class Cursant extends User {

    private int valabilitate;                        //0-cont dezactivat, 1- cont activ
    private String valabilitateText="";
    private double medie=0.0;
    private int nrNote=0;

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

    public String getValabilitateText() { return valabilitateText; }
    public void setValabilitateText(String valabilitateText) { this.valabilitateText = valabilitateText; }

    public double getMedie() { return medie; }
    public void setMedie(double medie) { this.medie = medie; }

    public int getNrNote() { return nrNote; }
    public void setNrNote(int nrNote) { this.nrNote = nrNote; }
}
