import java.util.*;
import org.sql2o.*;


public class Band {
	private String name;
	private int id;

	public Band(String name){
		this.name = name;

	}

	@Override
	public boolean equals(Object otherBand) {
		if (!(otherBand instanceof Band)) {
		return false;
	} else {
		Band newBand = (Band) otherBand;
		return this.getName().equals(newBand.getName()) &&
		this.getId() == newBand.getId();
		}
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public void save(){
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO bands(name) VALUES (:name)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.name)
			.executeUpdate()
			.getKey();
    	}
	}
}