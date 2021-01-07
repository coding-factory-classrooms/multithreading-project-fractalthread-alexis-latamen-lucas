package org.example;

import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.utils.Mandelbrot;
import spark.Spark;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        initialize();

        Mandelbrot mandelbrot = new Mandelbrot();
        try {
            ImageIO.write(
                mandelbrot.generate(1200, 900),
                "png",
                new File("src/main/resources/static/img/Mandelbrot/Mandelbrot1.jpg")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        Spark.get("/", (req, res) -> {
            return Template.render("home.html", new HashMap<>());
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
