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
import java.util.Map;


public class ZoomController {
    ArrayList<Mandelbrot> mandelbrotList = new ArrayList<Mandelbrot>();

    public final String in (Request req, Response res) {
        String result = req.queryParamOrDefault("zoom", "0") + 1;
        float zoom = Float.parseFloat(result);
        String filename = req.queryParams("filename");
        res.type("application/json");

        String basepath = "src/main/resources/static/img/";
        String folderName = "Mandelbrot/";
        String fileName = null;

        BufferedImage img = null;

        try {
            BufferedImage img3;
            Mandelbrot mandelbrot;
            // if we created pictures yet
            if(!mandelbrotList.isEmpty()) {
                mandelbrot = mandelbrotList.get(mandelbrotList.size() - 1);
                BufferedImage img1 = mandelbrot.create(mandelbrot.getWidth(), mandelbrot.getHeight());
                mandelbrot.zoomIn(zoom);

                img3 = mandelbrot.generate(img1);
            } else {
                // first time
                img = ImageIO.read(new File(basepath+folderName+filename));
                mandelbrot = new Mandelbrot();
                BufferedImage img2 = mandelbrot.create(img.getWidth(), img.getHeight());
                mandelbrot.zoomIn(zoom);

                img3 = mandelbrot.generate(img2);
            }

            mandelbrotList.add(mandelbrot);

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-S") ;
            fileName = "mandelbrot-"+dateFormat.format(date)+".jpg";

            ImageIO.write(
                    img3,
                    "jpg",
                    new File(basepath+folderName+fileName)
            );
            System.out.println(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "{\"type\": \"in\", \"value\":"+ result + ", \"filename\": \""  + fileName + "\" }";
    }

    public final String out (Request req, Response res) {
        String result = req.queryParamOrDefault("zoom", "0") + 1;
        float zoom = Float.parseFloat(result);
        String filename = req.queryParams("filename");
        res.type("application/json");

        String basepath = "src/main/resources/static/img/";
        String folderName = "Mandelbrot/";
        String fileName = null;

        BufferedImage img = null;
        try {
            BufferedImage img3;
            Mandelbrot mandelbrot;
            // if we created pictures yet
            if(!mandelbrotList.isEmpty()) {
                mandelbrot = mandelbrotList.get(mandelbrotList.size() - 1);
                BufferedImage img1 = mandelbrot.create(mandelbrot.getWidth(), mandelbrot.getHeight());
                mandelbrot.zoomOut(zoom);

                img3 = mandelbrot.generate(img1);
            } else {
                // first time
                img = ImageIO.read(new File(basepath+folderName+filename));
                mandelbrot = new Mandelbrot();
                BufferedImage img2 = mandelbrot.create(img.getWidth(), img.getHeight());
                mandelbrot.zoomOut(zoom);

                img3 = mandelbrot.generate(img2);
            }


            mandelbrotList.add(mandelbrot);

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-S") ;
            fileName = "mandelbrot-"+dateFormat.format(date)+".jpg";

            ImageIO.write(
                    img3,
                    "jpg",
                    new File(basepath+folderName+fileName)
            );
            System.out.println(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "{\"type\": \"in\", \"value\":"+ result + ", \"filename\": \""  + fileName + "\" }";
    }

    public final String move (Request req, Response res) {
        String direction = req.queryParamOrDefault("direction", "right");

        String filename = req.queryParams("filename");
        res.type("application/json");

        String basepath = "src/main/resources/static/img/";
        String folderName = "Mandelbrot/";
        String fileName = null;

        BufferedImage img = null;

        try {
            BufferedImage img3;
            Mandelbrot mandelbrot;
            // if we created pictures yet
            if(!mandelbrotList.isEmpty()) {
                mandelbrot = mandelbrotList.get(mandelbrotList.size() - 1);
                BufferedImage img1 = mandelbrot.create(mandelbrot.getWidth(), mandelbrot.getHeight());
                switch (direction) {
                    case "right":
                        mandelbrot.panRight();
                        break;
                    case "left":
                        mandelbrot.panLeft();
                        break;
                    case "up":
                        mandelbrot.panTop();
                        break;
                    case "down":
                        mandelbrot.panBot();
                        break;
                }

                img3 = mandelbrot.generate(img1);
            } else {
                // first time
                img = ImageIO.read(new File(basepath+folderName+filename));
                mandelbrot = new Mandelbrot();
                BufferedImage img2 = mandelbrot.create(img.getWidth(), img.getHeight());
                switch (direction) {
                    case "right":
                        mandelbrot.panRight();
                        break;
                    case "left":
                        mandelbrot.panLeft();
                        break;
                    case "up":
                        mandelbrot.panTop();
                        break;
                    case "down":
                        mandelbrot.panBot();
                        break;
                }

                img3 = mandelbrot.generate(img2);
            }

            mandelbrotList.add(mandelbrot);

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-S") ;
            fileName = "mandelbrot-"+dateFormat.format(date)+".jpg";

            ImageIO.write(
                    img3,
                    "jpg",
                    new File(basepath+folderName+fileName)
            );
            System.out.println(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("HELLO : " + fileName + " DIRECTION : " + direction);
        //return "{\"type\": \"in\", \"value\":"+ result + ", \"filename\": \""  + fileName + "\" }";
        return "{\"direction\": \""  + direction + "\", \"filename\": \"" + fileName + "\" }";
    }

    public final String resize (Request req, Response res) {
        String direction = req.queryParamOrDefault("direction", "right");

        Integer width = Integer.valueOf(req.queryParamOrDefault("width", "100"));
        Integer height = Integer.valueOf(req.queryParamOrDefault("height", "100"));

        String filename = req.queryParams("filename");
        res.type("application/json");

        String basepath = "src/main/resources/static/img/";
        String folderName = "Mandelbrot/";
        String fileName = null;

        BufferedImage img = null;

        try {
            BufferedImage img3;
            Mandelbrot mandelbrot;
            // if we created pictures yet
            if(!mandelbrotList.isEmpty()) {
                mandelbrot = mandelbrotList.get(mandelbrotList.size() - 1);
                BufferedImage img1 = mandelbrot.create(width, height);

                img3 = mandelbrot.generate(img1);
            } else {
                // first time
                img = ImageIO.read(new File(basepath+folderName+filename));
                mandelbrot = new Mandelbrot();
                BufferedImage img2 = mandelbrot.create(width, height);

                img3 = mandelbrot.generate(img2);
            }

            mandelbrotList.add(mandelbrot);

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-S") ;
            fileName = "mandelbrot-"+dateFormat.format(date)+".jpg";

            ImageIO.write(
                    img3,
                    "jpg",
                    new File(basepath+folderName+fileName)
            );
            System.out.println(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{\"filename\": \"" + fileName + "\" }";
    }

    public void clearMandelbrotList() {
        this.mandelbrotList.clear();
    }
}


