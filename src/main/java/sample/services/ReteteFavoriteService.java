package sample.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.entities.Reteta;
import sample.entities.RetetaFavorita;
import sample.entities.StatisticaNote;
import sample.exceptions.NumarMaximDeNoteAtinsException;
import sample.exceptions.RetetaDejaFavoritaException;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReteteFavoriteService {
    private static List<RetetaFavorita> reteteFavorite = new ArrayList<>();
    private static  boolean dejaParcurs = false;

    public static Object readJsonReteteFavorite(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();

        return jsonParser.parse(reader);
    }

    public static void parcurgereReteteFavorite() throws Exception {
        if(!dejaParcurs){
            JSONArray listaReteteFavorite = (JSONArray) readJsonReteteFavorite("ReteteFavorite.json");
            System.out.println("ReteteFavoriteService - parcurgereReteteFavorite");
            dejaParcurs=true;
            Iterator<JSONObject> iterator = listaReteteFavorite.iterator();
            while (iterator.hasNext()) {
                JSONObject retetaFavoritaJson = iterator.next();

                ArrayList<String> retete = (ArrayList<String>) retetaFavoritaJson.get("ListaReteteFavorite");
                RetetaFavorita rf = new RetetaFavorita(retetaFavoritaJson.get("username").toString(),retete );
                reteteFavorite.add(rf);
            }
        }
    }

    public static void writeJsonReteteFavorite(String filename) throws Exception {
        JSONArray reteteFavoriteWrite = new JSONArray();
        System.out.println("ReteteFavoriteService - writeJsonReteteFavorite");
        for(RetetaFavorita rf : reteteFavorite){
            JSONObject sampleObject = new JSONObject();

            sampleObject.put("username", rf.getUsername());
            JSONArray listaReteteFavoriteJson = new JSONArray();

            for(String reteta : rf.getReteteFavorite())
                listaReteteFavoriteJson.add(reteta);

            sampleObject.put("ListaReteteFavorite",listaReteteFavoriteJson);
            reteteFavoriteWrite.add(sampleObject);

        }

        Files.write(Paths.get(filename), reteteFavoriteWrite.toJSONString().getBytes());
    }

    public static void adaugaRetetaFavorita(String username, String reteta) throws Exception {
        parcurgereReteteFavorite();
        System.out.println("ReteteFavoriteService - adaugaRetetaFavorita" + reteteFavorite.size());
        if (reteteFavorite.size() == 0) {
            RetetaFavorita rf = new RetetaFavorita(username, new ArrayList());
            rf.getReteteFavorite().add(reteta);
            reteteFavorite.add(rf);
            System.out.println("adaugaRetetaFavorita -init - listaRetete " + rf.getReteteFavorite());
            writeJsonReteteFavorite("ReteteFavorite.json");
        } else {
            Boolean gasit = false;
            for (RetetaFavorita x : reteteFavorite) {
                System.out.println("ReteteFavoriteService-adaugaRetetaFavorita-for "+ x.getUsername());
                if (x.getUsername().equals(username)) {
                    if (x.getReteteFavorite().contains(reteta))
                       throw new RetetaDejaFavoritaException(reteta);
                    else {
                        x.getReteteFavorite().add(reteta);
                        System.out.println("adaugaRetetaFavorita -if - listaRetete " + x.getReteteFavorite());
                        writeJsonReteteFavorite("ReteteFavorite.json");
                        gasit = true;
                    }

                }
            }
            if(gasit== false){
                RetetaFavorita rf = new RetetaFavorita(username, new ArrayList());
                rf.getReteteFavorite().add(reteta);
                reteteFavorite.add(rf);
                System.out.println("adaugaRetetaFavorita -if2 - listaRetete " + rf.getReteteFavorite());
                writeJsonReteteFavorite("ReteteFavorite.json");
            }
        }
    }

    public static ArrayList<String> getReteteFavorite(String username) throws Exception {
        parcurgereReteteFavorite();
        for(RetetaFavorita rf: reteteFavorite){
            if(rf.getUsername().equals(username))
                return rf.getReteteFavorite();
        }
        return null;
    }


    public static void setReteteFavorite(List<RetetaFavorita> reteteFavorite) {
        ReteteFavoriteService.reteteFavorite = reteteFavorite;
    }


}
