package TP3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class metroParis {



	public static void main(String[] args) {

		readFile();

	}








	public static void readFile() {

		try {
			Stack<String> stack = new Stack<String>();
			Graph graph = new Graph();
			File myObj = new File("./src/metro.txt");
			Scanner myReader = new Scanner(myObj);


			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (data.contains("$"))
					break;

				String line[] = data.split(" ");
				int stationNum = Integer.parseInt(line[0]); 
				String stationName = data.substring(5);
				Station station = new Station(stationNum, stationName);
				graph.addStation(station); // Ajoute la station a la liste du graph
				graph.getStationsByNumber().put(stationNum, station); //Ajoute la sation au HashMap
				if (!stack.isEmpty() && stationName == stack.peek()) { //Si le nom est le meme que precedent, on ajoute une connection intra station de temps 0
					Edge edge1 = new Edge (stationNum, stationNum-1, 0);
					Edge edge2 = new Edge (stationNum-1, stationNum, 0);
					graph.addEdge(edge1);
					graph.addEdge(edge2);
					graph.getStationsByNumber().get(stationNum).addInConnection(edge2);
					graph.getStationsByNumber().get(stationNum-1).addInConnection(edge1);
					graph.getStationsByNumber().get(stationNum).addOutConnection(edge1);
					graph.getStationsByNumber().get(stationNum-1).addOutConnection(edge2);
				}
				else{ //On ajoute la station au stack(pour verifier apres le doublon) et au HashMap
				graph.getStationsByName().put(stationName, station);
				}
				stack.push(stationName);

			}

			while (myReader.hasNextLine()) { //On passe les lignes de coordonnees
				String data = myReader.nextLine();
				if (data.contains("$"))
					break;
			}

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String line[] = data.split(" ");

				Edge edge = new Edge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));

				graph.addEdge(edge);//Ajout des edges a la lise et au HashMap de chaque station concernee
				graph.getStationsByNumber().get(Integer.parseInt(line[0])).addOutConnection(edge);
				graph.getStationsByNumber().get(Integer.parseInt(line[1])).addInConnection(edge);

			}
			
			graph.stationsCritiques();
			graph.trajetLePlusRapideDijkstra("Alexandre Dumas", "Avron");
			graph.trajetLePlusRapideBellmanFord("Alexandre Dumas", "Avron");
			myReader.close();
			} catch (FileNotFoundException e) {
			System.out.println("Le fichier ne semble pas exister � l'emplacment donn�e.");
			e.printStackTrace();
		}

	}


	

}
