package org.example.controllers.api;

import org.example.utils.Mandelbrot;
import spark.Request;
import spark.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PanController {

    public final String move (Request req, Response res) {
        String direction = req.queryParamOrDefault("direction", "right");
        res.type("application/json");

        return "{\"direction\": \""  + direction + "\" }";
    }
}


