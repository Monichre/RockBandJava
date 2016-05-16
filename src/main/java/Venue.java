import java.util.*;
import org.sql2o.*;



public class Venue {
	private String name;
	private String city;
	private int id;

	public Venue(String name, String city) {
		this.name = name;
		this.city = city;
	}

	@Override
	public boolean equals(Object otherVenue) {
		if (!(otherVenue instanceof Venue)) {
		return false;
	} else {
		Venue newVenue = (Venue) otherVenue;
		return this.getName().equals(newVenue.getName()) &&
		this.getId() == newVenue.getId() &&
		this.getCity().equals(newVenue.getCity());
		}
	}

	public String getName(){
		return name;
	}

	public String getCity(){
		return city;
	}

	public int getId(){
		return id;
	}

	public void save(){
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO venues(name, city) VALUES (:name, :city)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.name)
			.addParameter("city", this.city)
			.executeUpdate()
			.getKey();
    	}
	}

	public static List<Venue> all(){
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT id, name, city FROM venues";
			return con.createQuery(sql)
			.executeAndFetch(Venue.class);
		}
	}

	public static Venue findVenue(int id){
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM venues WHERE id = :id";
			Venue venue = con.createQuery(sql)
			.addParameter("id", id)
			.executeAndFetchFirst(Venue.class);
			return venue;
		}
	}
	
	// // public void update(String property, String newValue){
	// // 	try(Connection con = DB.sql2o.open()) {
	// // 		String updateQuery = "UPDATE venues SET " + property + " = :newValue WHERE id = :id;";
	// // 		  con.createQuery(updateQuery)
	// // 		    .addParameter("id", this.getId())
	// // 		    .addParameter("newValue", newValue)
	// // 		    .executeUpdate();
	// // 	}

	// }

	// public void addBand(Band band){
	// 	try (Connection con = DB.sql2o.open()){
	// 		String sql = "INSERT INTO concerts (venue_id, band_id) VALUES (:venue_id, :band_id)";
	// 		con.createQuery(sql)
	// 		.addParameter("venue_id", this.getId())
	// 		.addParameter("band_id", band.getId())
	// 		.executeUpdate();
	// 	}
	// }

	// public List<Band> getBands(){
	// 	try(Connection con = DB.sql2o.open()){
	// 		String joinQuery = "SELECT band_id FROM concerts WHERE venue_id = :venue_id";
	// 		List<Integer> band_ids = con.createQuery(joinQuery)
	// 		.addParameter("venue_id", this.getId())
	// 		.executeAndFetch(Integer.class);

	// 		List<Band> bands = new ArrayList<Band>();

	// 		for(Integer band_id : band_ids){
	// 			String bandQuery = "SELECT * FROM bands WHERE id = :band_id";
	// 			Band band = con.createQuery(bandQuery)
	// 			.addParameter("band_id", band_id)
	// 			.executeAndFetchFirst(Band.class);
	// 			bands.add(band);
	// 		}
	// 		return bands;
	// 	}
	// }
}