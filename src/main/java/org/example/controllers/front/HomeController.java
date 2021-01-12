package org.example.controllers.front;

import org.example.controllers.api.ZoomController;
import org.example.core.Template;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController {

    public final String list (Request req, Response res) {
        Map<String, Object> model = new HashMap<>();

        // clear mandelbrot list
        ZoomController zoomController = new ZoomController();
        zoomController.clearMandelbrotList();

        return Template.render("home.html", model);
    }
}
