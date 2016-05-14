
import org.sql2o.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BandTest {

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Test 
	public void Band_instantiates_Correctly(){
		Band newBand = new Band("Incubus");
		assertTrue(newBand instanceof Band);
	}

	@Test 
	public void getName_returnsName(){
		Band newBand = new Band("Incubus");
		newBand.save();
		assertEquals("Incubus", Band.all().get(0).getName());
	}

	@Test
	public void equals_returnsTrueIfDescriptionsAretheSame() {
		Band firstBand = new Band("Mow the lawn");
		Band secondBand = new Band("Mow the lawn");
		assertTrue(firstBand.equals(secondBand));
	}

	@Test 
	public void save_addsToDatabase(){
		Band newBand = new Band("Incubus");
		newBand.save();
		assertTrue(newBand.equals(Band.all().get(0)));
	}

	@Test
	public void save_assignsIdToObject() {
		Band myBand = new Band("Incubus");
		myBand.save();
		Band savedBand = Band.all().get(0);
		assertEquals(myBand.getId(), savedBand.getId());
	}

	@Test 
	public void all_returnsAllBands(){
		Band newBand = new Band("Incubus");
		newBand.save();
		Band newBand2 = new Band("Foals");
		newBand2.save();
		assertEquals(2, Band.all().size());
	}

	@Test 
	public void find_returnsBand(){
		Band newBand = new Band("Incubus");
		newBand.save();
		assertEquals(newBand, Band.findBand(newBand.getId()));
	}

	@Test
	public void addVenues_addVenuestoBand() {
		Band myBand = new Band("Fleet Foxes");
		myBand.save();
		Venue myVenue = new Venue("Red Rocks", "Denver");
		myVenue.save();
		myBand.addVenue(myVenue);
		Venue savedVenue = myBand.getVenues().get(0);
		assertTrue(myVenue.equals(savedVenue));
	}

	@Test
	public void getVenues_returnsAllVenues_List() {
		Band myBand = new Band("Fleet Foxes");
		myBand.save();
		Venue myVenue = new Venue("Red Rocks", "Denver");
		myVenue.save();
		myBand.addVenue(myVenue);
		List savedVenues = myBand.getVenues();
		assertEquals(1, savedVenues.size());
	}
	@Test
	public void delete_deletesAllTasksAndCategoriesAssociations() {
		Band myBand = new Band("Household chores");
		myBand.save();
		Venue myVenue = new Venue("Mow the lawn", "Burnsville");
		myVenue.save();
		myBand.addVenue(myVenue);
		myBand.delete();
		assertEquals(0, myVenue.getBands().size());
	}

}
