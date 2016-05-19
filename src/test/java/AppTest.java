import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("AudioPhile");
  }

  @Test
  public void bandIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#bandName").with("Incubus");
    fill("#venueName").with("Red Rocks");
    fill("#city").with("Denver");
    submit(".btn");
    assertThat(pageSource()).contains("Incubus");
  }

  @Test
  public void bandsLinkWorks() {
    goTo("http://localhost:4567/");
    fill("#bandName").with("Incubus");
    fill("#venueName").with("Red Rocks");
    fill("#city").with("Denver");
    submit(".btn");
    assertThat(pageSource()).contains("Incubus");
    click("a", withText("Home"));
    assertThat(pageSource()).contains("AudioPhile");
    click("a", withText("Your bands"));
    assertThat(pageSource()).contains("Incubus");
  }
  @Test
  public void venuesLinkWorks() {
    goTo("http://localhost:4567/");
    fill("#bandName").with("Incubus");
    fill("#venueName").with("Red Rocks");
    fill("#city").with("Denver");
    submit(".btn");
    assertThat(pageSource()).contains("Incubus");
    click("a", withText("Home"));
    assertThat(pageSource()).contains("AudioPhile");
    click("a", withText("Your venues"));
    assertThat(pageSource()).contains("Red Rocks");

  }

  @Test
  public void PageDisplaysBandName() {
    Band testBand = new Band("Tool");
    testBand.save();
    Venue testVenue = new Venue("MSG", "NYC");
    testVenue.save();
    testBand.addVenue(testVenue);
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("Tool");
    
  }

  @Test
  public void pageDisplaysVenue() {
    Band testBand = new Band("CSNY");
    testBand.save();
    Venue testVenue = new Venue("MSG", "NYC");
    testVenue.save();
    testBand.addVenue(testVenue);
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("MSG, NYC");
  }

  @Test
  public void sameVenueNotAdded() {
    Band testBand = new Band("CSNY");
    testBand.save();
    Venue testVenue = new Venue("MSG", "NYC");
    testVenue.save();
    testBand.addVenue(testVenue);
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    fill("#newVenue").with("MSG");
    fill("#newCity").with("NYC");
    submit(".btn");
    assertThat(pageSource()).contains("500 Internal Error");
  }

  @Test
  public void bandNameIsUpdated() {
    Band testBand = new Band("Incubs");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    click("a", withText("Edit this band"));
    assertThat(pageSource()).contains("Edit Name");
    fill("#editName").with("Incubus");
    submit(".btn");
    assertThat(pageSource()).contains("Incubus");
  }

  @Test
  public void bandsListsBands() {
    Band testBand = new Band("Incubus");
    testBand.save();
    Band testBand2 = new Band("CSNY");
    testBand2.save();
    String url = String.format("http://localhost:4567/bands");
    goTo(url);
    assertThat(pageSource()).contains("CSNY");
    assertThat(pageSource()).contains("Incubus");
  }

  @Test
  public void bandIsDeleted() {
    Band testBand = new Band("House");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    submit("#delete");
    goTo(url);
    assertThat(pageSource()).contains("$newBand.getName()");
  }

}

