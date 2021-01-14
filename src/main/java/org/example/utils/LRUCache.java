package org.example.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache {
    private Map<Float, BufferedImage> fractals;
    //private final int max = 20;

    public LRUCache() {
        fractals = new HashMap<Float, BufferedImage>();
    }

    public void put(Float precision, BufferedImage fractal) {
        this.fractals.put(precision, fractal);
    }

    public BufferedImage get(Float precision) {
        return this.fractals.get(precision);
    }
}
