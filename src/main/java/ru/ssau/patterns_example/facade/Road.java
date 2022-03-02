package ru.ssau.patterns_example.facade;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Road {
    private static final String ROAD_IMAGE_PATH = "src/main/resources/road.png";
    private volatile BufferedImage roadPicture;

    public Road() {
        try {
            this.roadPicture = ImageIO.read(new File(ROAD_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getRoadPicture() {
        return this.roadPicture;
    }
}
