package org.example.controllers;

import org.apache.velocity.texen.util.FileUtil;
import org.example.utils.Mandelbrot;
import spark.Request;
import spark.Response;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MandelbrotController {
    List<Long> delays = new ArrayList<Long>();

    public String createMandelbrot(){
        Mandelbrot mandelbrot = new Mandelbrot();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-S") ;
        String fileName = "src/main/resources/static/img/Mandelbrot/mandelbrot-"+dateFormat.format(date)+".jpg";
        try {
            long start = System.currentTimeMillis();
            ImageIO.write(
                    mandelbrot.generate(1200, 900),
                    "jpg",
                    new File(fileName)
            );
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Temps : " + elapsed);
            this.delays.add(elapsed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public long calculateAverage() {
        long sum = 0;
        if(!delays.isEmpty()) {
            for (long delay : delays) {
                sum += delay;
            }
            return sum / delays.size();
        }
        return sum;
    }

    public byte[] home (Request request, Response response) {
        String pathToFile = createMandelbrot();

        File file = new File(pathToFile);
        response.type("image/jpg");
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
