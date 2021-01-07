package org.example.controllers;

import org.example.utils.Mandelbrot;

import javax.imageio.ImageIO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MandelbrotController {
    List<Long> delays = new ArrayList<Long>();

    public void createMandelbrot(){
        Mandelbrot mandelbrot = new Mandelbrot();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-S") ;
        try {
            long start = System.currentTimeMillis();
            ImageIO.write(
                    mandelbrot.generate(1000, 1000),
                    "jpg",
                    new File("src/main/resources/static/img/Mandelbrot/mandelbrot-"+dateFormat.format(date)+".jpg")
            );
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Temps : " + elapsed);
            this.delays.add(elapsed);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
