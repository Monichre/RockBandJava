import java.util.HashMap;
import java.util.Map;
import java.util.List;
import spark.ModelAndView;
import java.util.ArrayList;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");

      get("/", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/home.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

      post("/bands", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String bandName = request.queryParams("bandName");
        Band newBand = new Band(bandName);
        newBand.save();
        String venueName = request.queryParams("venueName");
        String city = request.queryParams("city");
        Venue newVenue = new Venue(venueName, city);
        newVenue.save();
        newBand.addVenue(newVenue);
        model.put("newBand", newBand);
        model.put("template", "templates/bands.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

      get("/bands/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Band newBand = Band.findBand(Integer.parseInt(request.params(":id")));
        model.put("newBand", newBand);
        model.put("template", "templates/bandpage.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

      // post("/bands/:id", (request, response) -> {
      //   Map<String, Object> model = new HashMap<String, Object>();
      //   Band newBand = Band.findBand(Integer.parseInt(request.params(":id")));      
      //   model.put("newBand", newBand);
      //   model.put("template", "templates/bandpage.vtl");
      //   return new ModelAndView(model, "templates/layout.vtl");
      // }, new VelocityTemplateEngine());

      post("/bands/:id/newVenue", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Band newBand = Band.findBand(Integer.parseInt(request.params(":id")));
        newBand.save();
        String newVenue = request.queryParams("newVenue");
        String newCity = request.queryParams("newCity");
        Venue newVenueObject = new Venue(newVenue, newCity);
        newVenueObject.save();
        newBand.addVenue(newVenueObject);
        model.put("newBand", newBand);
        model.put("template", "templates/bandpage.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

  }



}
