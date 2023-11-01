package TP3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Graph {

    private List<Station> stations;
    private List<Edge> edges;

    private HashMap<String, Station> stationsByName; // HashMap pour trouver une station par son nom
    private HashMap<Integer, Station> stationsByNumber; // HashMap pour trouver une station par son numero

    public Graph() {
        stations = new ArrayList<>();
        edges = new ArrayList<>();
        stationsByName = new HashMap<>();
        stationsByNumber = new HashMap<>();

    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public HashMap<String, Station> getStationsByName() {
        return stationsByName;
    }

    public HashMap<Integer, Station> getStationsByNumber() {
        return stationsByNumber;
    }

    // retourne la liste des stations qui, si elles sont ferm�s, rendent au moins
    // une station innateignable
    public ArrayList<Station> stationsCritiques() {

        ArrayList<Station> stationsCritiques = new ArrayList<Station>();
        int count = 0;
        for (Station station : stations) {
            if (station.getInConnections().size() == 1) {
                Station laStationCritique = getStationsByNumber().get(station.getInConnections().get(0).getDeparture());
                stationsCritiques.add(laStationCritique);
                count++;
            }
        }
        System.out.println('\n' + "Les " + count + " stations critiques sont : \n");
        for (Station station : stationsCritiques) {
            System.out.println(station.getName());
        }
        return stationsCritiques;

    }

    // retourne le trajet le plus rapide entre 2 stations, et le temps requis pour
    // ce trajet.
    public Trajet trajetLePlusRapideDijkstra(String debut, String fin) {

        Trajet trajet = new Trajet();
        Station current = getStationsByName().get(debut);
        current.setDistance(0);
        Station finish = getStationsByName().get(fin);
        trajet.addTempsRequis(0);
        Stack<Station> stack = new Stack<Station>();
        stack.push(current);

        while (!stack.isEmpty()) {
            current.setVisited(true);// Prend toutes les edges qui partent de la station actuelle
            ArrayList<Edge> next = current.getOutConnections();
            for (Edge edge : next) {// Verifie si deja dans stack ou deja visitee
                if (getStationsByNumber().get(edge.getArrival()).isVisited() == false
                        && getStationsByNumber().get(edge.getArrival()).isComingNext() == false) {
                    stack.push(getStationsByNumber().get(edge.getArrival()));
                    getStationsByNumber().get(edge.getArrival()).setComingNext(true);
                } // Set les distances du depart de chaque station connexes, ainsi que le
                  // predecesseur
                if (getStationsByNumber().get(edge.getArrival()).getDistance() > current.getDistance()
                        + edge.getTime()) {
                    getStationsByNumber().get(edge.getArrival()).setDistance(current.getDistance() + edge.getTime());
                    getStationsByNumber().get(edge.getArrival()).setPrevious(current);
                }
            }
            current = stack.pop();
        }

        Station station = finish;
        while (station != null) { // Creer le trajet avec le predecesseur de chaque station
            trajet.addStation(station);
            station = station.getPrevious();
        }
        trajet.addTempsRequis(finish.getDistance());

        System.out.println(trajet.toString());
        return trajet;

    }

    // // retourne le trajet le plus rapide entre 2 stations, et le temps requis
    // pour
    // // ce trajet, avec l'algo de BellManFord.

    public Trajet trajetLePlusRapideBellmanFord(String debut, String fin) {
        Trajet trajet = new Trajet();
        Station current = getStationsByName().get(debut);
        current.setDistance(0);
        Station finish = getStationsByName().get(fin);
        trajet.addTempsRequis(0);

        for (int i = 1; i < getStationsByNumber().size(); i++) {
            for (Station station : getStationsByNumber().values()) {
                ArrayList<Edge> edges = station.getOutConnections();
                for (Edge edge : edges) {
                    Station destination = getStationsByNumber().get(edge.getArrival());
                    if (station.getDistance() != 100000
                            && station.getDistance() + edge.getTime() < destination.getDistance()) {
                        destination.setDistance(station.getDistance() + edge.getTime());
                        destination.setPrevious(station);
                    }
                }
            }
        }

        for (Station station : getStationsByNumber().values()) { // Recherche de cycle negatif
            ArrayList<Edge> edges = station.getOutConnections();
            for (Edge edge : edges) {
                Station destination = getStationsByNumber().get(edge.getArrival());
                if (station.getDistance() != 100000
                        && station.getDistance() + edge.getTime() < destination.getDistance()) {
                    throw new RuntimeException("Cycle negatif");
                }
            }
        }

        Station station = finish;
        while (station != null) {
            trajet.addStation(station);
            station = station.getPrevious();
        }
        trajet.addTempsRequis(finish.getDistance());

        System.out.println(trajet.toString());
        return trajet;
    }

}
