package sample.entities;

import java.util.ArrayList;

public class StatisticaNote {

    private String username;
    private ArrayList<Double> note;
    private  double medie;

    public StatisticaNote(String username, ArrayList<Double> note) {
        this.username = username;
        this.note = note;
    }

    public double calculMedie(){
        double sum=0;
        for(Double nota : note)
            sum+=nota;
        medie = sum/note.size();
        return medie ;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Double> getNote() {
        return note;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNote(ArrayList<Double> note) {
        this.note = note;
    }
}
