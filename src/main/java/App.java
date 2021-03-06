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

      get("/bands", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("bands", Band.all());
        model.put("template", "templates/bands.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

      post("/bands", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String bandName = request.queryParams("bandName");
        if(bandName.equals("") == true){
          return null;
        } 

        for(Band band : Band.all()){
          if(bandName.equals(band.getName())){
            return null;
          }
        }  
        Band newBand = new Band(bandName);
        newBand.save();
        String venueName = request.queryParams("venueName");
        String city = request.queryParams("city");
        if(venueName.equals("") == true){
          return null;
        }
        
        if (Venue.venueIsEntered(venueName) == false){
          Venue newVenue = new Venue(venueName, city);
          newVenue.save();
          newBand.addVenue(newVenue);
          newVenue.addBand(newBand);
        } else {
          for(Venue venue : Venue.all()){
            if(venueName.equals(venue.getName())){
              newBand.addVenue(venue);
            }
          }
        }
        model.put("bands", Band.all());
        response.redirect("/bands/" + newBand.getId());
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

      get("/bands/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Band newBand = Band.findBand(Integer.parseInt(request.params(":id")));
        model.put("newBand", newBand);
        model.put("template", "templates/bandpage.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());


      post("/bands/:id/newVenue", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Band newBand = Band.findBand(Integer.parseInt(request.params(":id")));
        String newVenue = request.queryParams("newVenue");
        String newCity = request.queryParams("newCity");
        if(newVenue.equals("") == true){
          return null;
        }

        if (Venue.venueIsEntered(newVenue) == false){
          Venue newVenueObject = new Venue(newVenue, newCity);
          newVenueObject.save();
          newBand.addVenue(newVenueObject);
          newVenueObject.addBand(newBand);
        }else {
          for(Venue venue : Venue.all()){
            if(newVenue.equals(venue.getName())){
              newBand.addVenue(venue);
            }
          }
        }
        
        
        response.redirect("/bands/" + newBand.getId() + "/newVenue");
        return null;
      });

      get("/bands/:id/newVenue", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Band newBand = Band.findBand(Integer.parseInt(request.params(":id")));
        model.put("newBand", newBand);
        model.put("template", "templates/bandpage.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

      get("/venues", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("venues", Venue.all());
        model.put("template", "templates/venues.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

      post("/venues", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("venues", Venue.all());
        model.put("template", "templates/venues.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

      get("/venues/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Venue newVenue = Venue.findVenue(Integer.parseInt(request.params(":id")));
        model.put("newVenue", newVenue);
        model.put("template", "templates/venue.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());


      get("/bands/:id/edit", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Band newBand = Band.findBand(Integer.parseInt(request.params(":id")));
        model.put("newBand", newBand);
        model.put("template", "templates/edit-band.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }, new VelocityTemplateEngine());

      post("/bands/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        int bandId = Integer.parseInt(request.params(":id"));
        Band band = Band.findBand(bandId);
        String newName = request.queryParams("editName");
        band.update("name", newName);
        response.redirect("/bands/" + bandId);
        return null;
      });

      post("/bands/:id/delete", (request,response) -> {
        int bandId = Integer.parseInt(request.params("id"));
        Band band = Band.findBand(bandId);
        band.delete();
        response.redirect("/bands");
        return null;
      });

  }
}
