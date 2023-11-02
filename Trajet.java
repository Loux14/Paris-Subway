
import java.util.ArrayList;

public class Trajet {

	private ArrayList<Station> ListeDesStationsDuTrajet = new ArrayList<Station>();
	private int tempsRequis;

	public ArrayList<Station> getListeDesStationsDuTrajet() {
		return ListeDesStationsDuTrajet;
	}

	public void addStation(Station station) {
		if (!ListeDesStationsDuTrajet.contains(station))
			ListeDesStationsDuTrajet.add(station);
	}

	public int getTempsRequis() {
		return tempsRequis;
	}

	public void addTempsRequis(int tempsRequis) {
		this.tempsRequis += tempsRequis;
	}

	public String toString() {
		// toString pour afficher le trajet sans afficher 2 fois une stations avec
		// changement de ligne
		String s = "\n\nLe trajet est le suivant : \n\n";
		String previousStationName = null;

		for (int i = ListeDesStationsDuTrajet.size() - 1; i >= 0; i--) {
			Station station = ListeDesStationsDuTrajet.get(i);
			String currentStationName = station.getName();
			if (!currentStationName.equals(previousStationName)) {
				s += currentStationName + "\n";
				previousStationName = currentStationName;
			}
		}

		s += "\net le temps requis est de " + (tempsRequis / 60) + " minutes et " + (tempsRequis % 60) + " secondes.\n";
		return s;
	}

}
