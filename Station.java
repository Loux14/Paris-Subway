
import java.util.ArrayList;

 

public class Station {
	
	private String name;
	private int number;
	private ArrayList<Edge> inConnections; // Toutes les connections entrantes
	private ArrayList<Edge> outConnections;// Toutes les connections sortantes


	private boolean visited = false; //status pour le dijkstra
	private boolean comingNext = false; //status pour le dijkstra
	private int distance = 100000; //pseudo infini pour les algos
	private Station previous;



	public Station(int number, String name) {
		this.name = name;
		this.number = number;
		this.inConnections = new ArrayList<Edge>();
		this.outConnections = new ArrayList<Edge>();

	}


	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public ArrayList<Edge> getInConnections() {
		return inConnections;
	}

	public ArrayList<Edge> getOutConnections() {
		return outConnections;
	}

	public void addInConnection(Edge edge) {
		inConnections.add(edge);
	}

	public void addOutConnection(Edge edge) {
		outConnections.add(edge);
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}	


	public boolean isComingNext() {
		return comingNext;
	}

	public void setComingNext(boolean comingNext) {
		this.comingNext = comingNext;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Station getPrevious() {
		return previous;
	}

	public void setPrevious(Station previous) {
		this.previous = previous;
	}
 

	public String toString() {
		return "Station " + name + " (" + number + ")";
	}

}
