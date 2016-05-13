
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

  // @Test
  // public void addTask_addVenuestoVenue() {
  //   Venue myVenue = new Venue("Fleet Foxes");
  //   myVenue.save();
  //   Venue myVenue = new Venue("Red Rocks", "Denver");
  //   myVenue.save();
  //   myVenue.addVenue(myVenue);
  //   Venue savedVenue = myVenue.getVenues().get(0);
  //   assertTrue(myVenue.equals(savedVenue));
  // }
  
  // @Test
  // public void getVenues_returnsAllVenues_List() {
  //   Venue myVenue = new Venue("Fleet Foxes");
  //   myVenue.save();
  //   Venue myVenue = new Venue("Red Rocks", "Denver");
  //   myVenue.save();
  //   myVenue.addVenue(myVenue);
  //   List savedVenues = myVenue.getVenues();
  //   assertEquals(1, savedVenues.size());
  // }



}
