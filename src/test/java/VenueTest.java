import java.util.*;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test 
  public void Venue_instantiates_Correctly(){
    Venue newVenue = new Venue("Red Rocks", "Denver");
    assertTrue(newVenue instanceof Venue);
  }

  @Test 
  public void getName_returnsName(){
    Venue newVenue = new Venue("Red Rocks", "Denver");
    newVenue.save();
    assertEquals("Red Rocks", Venue.all().get(0).getName());
  }

  @Test 
  public void save_addsToDatabase(){
    Venue newVenue = new Venue("Red Rocks", "Denver");
    newVenue.save();
    assertEquals(newVenue, Venue.all().get(0));
  }

  @Test
  public void save_assignsIdToObject() {
    Venue newVenue = new Venue("Red Rocks", "Denver");
    newVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(newVenue.getId(), savedVenue.getId());
  }

  @Test 
  public void all_returnsAllVenues(){
    Venue newVenue = new Venue("Red Rocks", "Denver");
    newVenue.save();
    Venue newVenue2 = new Venue("Terminal 5", "NYC");
    newVenue2.save();
    assertEquals(2, Venue.all().size());
  }

  @Test 
  public void find_returnsVenue(){
    Venue newVenue = new Venue("Red Rocks", "Denver");
    newVenue.save();
    assertEquals(newVenue, Venue.findVenue(newVenue.getId()));
  }

  @Test 
  public void find_doesNotReturnDuplicates(){
    Venue newVenue = new Venue("Red Rocks", "Denver");
    newVenue.save();
    String venueName = "Red Rocks";
    assertEquals(true, Venue.venueIsEntered(venueName));
  }

  @Test
  public void addBands_addBandsToVenues() {
    Band myBand = new Band("Fleet Foxes");
    myBand.save();
    Venue myVenue = new Venue("Red Rocks", "Denver");
    myVenue.save();
    myVenue.addBand(myBand);
    Band savedBand = myVenue.getBands().get(0);
    assertTrue(myBand.equals(savedBand));
  }
  
  @Test
  public void getVenues_returnsAllVenues_List() {
    Band myBand = new Band("Fleet Foxes");
    myBand.save();
    Venue myVenue = new Venue("Red Rocks", "Denver");
    myVenue.save();
    myVenue.addBand(myBand);
    List savedBands = myVenue.getBands();
    assertEquals(1, savedBands.size());
  }



}
