
import org.sql2o.*;
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
	public void Band_getNameReturnsName(){
		Band newBand = new Band("Incubus");
		newBand.save();
		assertEquals("Incubus", Band.all().get(0).getName());
	}

	@Test 
	public void Band_savesToDatabase(){
		Band newBand = new Band("Incubus");
		newBand.save();
		assertEquals(newBand, Band.all().get(0));
	}

	@Test 
	public void Band_allReturnsAllBands(){
		Band newBand = new Band("Incubus");
		newBand.save();
		Band newBand2 = new Band("Foals");
		newBand2.save();
		assertEquals(2, Band.all().size());
	}

}
