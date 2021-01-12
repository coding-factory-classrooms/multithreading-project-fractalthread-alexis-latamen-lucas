package org.example.controllers.front;

import org.example.core.Template;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController {

    public final String list (Request req, Response res) {
        Map<String, Object> model = new HashMap<>();

        return Template.render("home.html", model);
    }
}
