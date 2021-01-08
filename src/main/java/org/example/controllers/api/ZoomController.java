package org.example.controllers.api;

import spark.Request;
import spark.Response;


public class ZoomController {

    public final String in (Request req, Response res) {
        String result = req.queryParamOrDefault("zoom", "0") + 1;
        res.type("application/json");

        return "{\"type\": \"in\", \"value\":"+ result + "}";
    }

    public final String out (Request req, Response res) {
        String result = req.queryParamOrDefault("zoom", "0");
        res.type("application/json");

        return "{\"type\": \"out\", \"value\":"+ result + "}";
    }

}


