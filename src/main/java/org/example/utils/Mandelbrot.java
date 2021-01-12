package org.example.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

// 2018 TheFlyingKeyboard and released under MIT License
// theflyingkeyboard.net
public class Mandelbrot {
    /* Real sets (between -1 and 0.5) */
    private double reMin = -2.0d;
    private double reMax = 1.0d;

    /* Imaginary sets (between -0.5 and 0.5) */
    private double imMin = -1.2d;
    private double imMax = 1.2d;

    private int[] imagePixelData;
    private int[] colors;
    private int convergenceSteps;
    private int width;
    private int height;

    public BufferedImage create(int width, int height) {
        this.width = width;
        this.height = height;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imagePixelData = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
        return bufferedImage;
    }

    public BufferedImage generate(BufferedImage bufferedImage) {

        double precision = Math.max((reMax - reMin) / width, (imMax - imMin) / height);
        convergenceSteps = (int) (1 / precision);

        colors = new int[(int) (1 / precision)];

        for (int i = 0; i < 1 / precision - 1; ++i) {
            colors[i] = Color.HSBtoRGB(0.7f, 1, i / (i + 50f));
        }

        double counter = 0;
        int convergenceValue;

        for (double mandelbrotReal = reMin, x = 0; x < width; mandelbrotReal += precision, ++x) {
            for (double mandelbrotImaginary = imMin, y = 0; y < height; mandelbrotImaginary += precision, ++y) {
                convergenceValue = calculateConvergence(mandelbrotReal, mandelbrotImaginary, 5000);
                colorPixel(x, y, convergenceValue);

                ++counter;
            }

            //System.out.println((counter / (width * height)) * 100.0f + "%");
        }

        return bufferedImage;
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

    public double getReMin() {
        return reMin;
    }

    public double getReMax() {
        return reMax;
    }

    public double getImMin() {
        return imMin;
    }

    public double getImMax() {
        return imMax;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void zoomIn(float zoom) {
        this.imMin = this.imMin + zoom;
        this.imMax = this.imMax - zoom;
        this.reMin = this.reMin + zoom;
        this.reMax = this.reMax - zoom;
    }

    public void zoomOut(float zoom) {
        this.imMin = this.imMin - zoom;
        this.imMax = this.imMax + zoom;
        this.reMin = this.reMin - zoom;
        this.reMax = this.reMax + zoom;
    }

    public void panTop() {
        this.imMin = this.imMin + 0.5;
        this.imMax = this.imMax + 0.5;
    }

    public void panBot() {
        this.imMin = this.imMin - 0.5;
        this.imMax = this.imMax - 0.5;
    }

    public void panRight() {
        this.reMin = this.reMin - 0.5;
        this.reMax = this.reMax - 0.5;
    }

    public void panLeft() {
        this.reMin = this.reMin + 0.5;
        this.reMax = this.reMax + 0.5;
    }
}