package sample.Entities;

public class Curs {
    private String titlu;
    private String descriere;
    private double cost;
    private int nrParticipanti;

    public Curs(String titlu, String descriere, double cost, int nrParticipanti) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.cost = cost;
        this.nrParticipanti = nrParticipanti;
    }

    public String getTitlu() { return titlu; }
    public void setTitlu(String titlu) { this.titlu = titlu; }

    public String getDescriere() { return descriere; }
    public void setDescriere(String descriere) { this.descriere = descriere; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public int getNrParticipanti() { return nrParticipanti; }
    public void setNrParticipanti(int nrParticipanti) { this.nrParticipanti = nrParticipanti; }

}
