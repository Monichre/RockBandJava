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

  // @Test
  // public void venueIsCreatedTest() {
  //   goTo("http://localhost:4567/");
  //   click("a", withText("Venues"));
  //   fill("#description").with("Tavern");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Tavern");
  // }

  // @Test
  // public void bandShowPageDisplaysName() {
  //   Band testBand = new Band("House");
  //   testBand.save();
  //   String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
  //   goTo(url);
  //   assertThat(pageSource()).contains("House");
  // }

  @Test
  public void venueIsAddedToBand() {
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

  // @Test
  // public void bandIsDeleted() {
  //   Band testBand = new Band("House");
  //   testBand.save();
  //   String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
  //   goTo(url);
  //   submit("#delete");
  //   goTo(url);
  //   assertThat(pageSource()).contains("$band.getName()");
  }

