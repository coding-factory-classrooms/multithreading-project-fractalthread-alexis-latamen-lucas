package org.example.utils;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class ImageTask implements Callable<BufferedImage> {

    private BufferedImage pieceOfImage;
    private int[] imagePixelData;
    private int[] colors;
    private int convergenceSteps;
    private int width;
    private int height;

    private double imMin;
    private double mandelbrotReal;
    private double precision;
    private double x;

    public ImageTask(BufferedImage pieceOfImage, int[] imagePixelData, int[] colors, int convergenceSteps, int width, int height, double imMin, double mandelbrotReal, double precision, double x) {
        this.pieceOfImage = pieceOfImage;
        this.imagePixelData = imagePixelData;
        this.colors = colors;
        this.convergenceSteps = convergenceSteps;
        this.width = width;
        this.height = height;
        this.imMin = imMin;
        this.mandelbrotReal = mandelbrotReal;
        this.precision = precision;
        this.x = x;
    }

    @Override
    public BufferedImage call() throws Exception {
        int convergenceValue;

        for (double mandelbrotImaginary = imMin, y = 0; y < height; mandelbrotImaginary += precision, ++y) {
            // System.out.println("mandelbrotImaginary : " + mandelbrotImaginary);

            convergenceValue = calculateConvergence(mandelbrotReal, mandelbrotImaginary, 5000);
            colorPixel(x, y, convergenceValue);

            //++counter;
        }

        return pieceOfImage;
    }

    private void colorPixel(double x, double y, int convergence) {
        if (convergence < convergenceSteps) {
            imagePixelData[(int) y * width + (int) x] = colors[convergence];
        } else {
            imagePixelData[(int) y * width + (int) x] = 0;
        }
    }

    private int calculateConvergence(double mandelbrotReal, double mandelbrotImaginary, int maxSteps) {
        double x = 0;
        double y = 0;
        int step = 0;
        double newX;

        while (x * x + y * y < 4 && step < maxSteps) {
            newX = x * x - y * y + mandelbrotReal;
            y = 2 * x * y + mandelbrotImaginary;
            x = newX;

            ++step;
        }

        return step;
    }
}
