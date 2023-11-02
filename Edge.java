
public class Edge {

    private int departure;
    private int arrival;
    private int time;

    public Edge(int departure, int arrival, int time) {
        this.departure = departure;
        this.arrival = arrival;
        this.time = time;
    }

    public int getDeparture() {
        return departure;
    }

    public int getArrival() {
        return arrival;
    }

    public int getTime() {
        return time;
    }

    public String toString() {
        return "Edge{" + "departure=" + departure + ", arrival=" + arrival + ", time=" + time + '}';
    }
    
}
