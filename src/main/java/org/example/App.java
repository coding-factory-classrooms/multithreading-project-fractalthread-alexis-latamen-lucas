package org.example;

import org.example.controllers.MandelbrotController;
import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.utils.Mandelbrot;
import spark.Spark;

import javax.imageio.ImageIO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        initialize();

        MandelbrotController mandelbrotController = new MandelbrotController();

        Spark.get("/", mandelbrotController::home);

        Spark.get("/create-mandelbrot", (req, res) -> {
            // mandelbrot jpg file creation
            for(int i = 0; i < 10; i++) {
                mandelbrotController.createMandelbrot();
            }
            return "Temps moyen de création d'un mandelbrot : " + mandelbrotController.calculateAverage();
        });

    }

    static void initialize() {
        Template.initialize();

        // Display exceptions in logs
        Spark.exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // Serve static files (img/css/js)
        Spark.staticFiles.externalLocation(Conf.STATIC_DIR.getPath());

        // Configure server port
        Spark.port(Conf.HTTP_PORT);
        final LoggerMiddleware log = new LoggerMiddleware();
        Spark.before(log::process);
    }
}
