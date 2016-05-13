
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BandTest {

@Rule
public DatabaseRule database = new DatabaseRule();

@Test 
public void Band_Instantiates_Correctly(){
	Band newBand = new Band("Incubus");
	assertTrue(newBand instanceof Band);
}

}
