package org.example;

import org.example.controllers.api.ZoomController;
import org.example.controllers.front.HomeController;
import org.example.controllers.MandelbrotController;
import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        initialize();

        HomeController homeController = new HomeController();
        ZoomController zoomController = new ZoomController();
        MandelbrotController mandelbrotController = new MandelbrotController();

        Spark.get("/", homeController::list);
        Spark.get("/api/mandelbrot/in", zoomController::in);
        Spark.get("/api/mandelbrot/out", zoomController::out);
        Spark.get("/mandelbrot", mandelbrotController::home);

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
