package ru.ssau.patterns_example.facade;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Car {
    private static final String CAR_PATH = "src/main/resources/car.png";
    private static final String CAR_2 = "src/main/resources/car2.png";
    private volatile BufferedImage carPicture;
    private volatile int carPosition = -80;

    public Car() {
        try {
            carPicture = ImageIO.read(new File(CAR_2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getPicture() {
        return this.carPicture;
    }

    public int getPosition() {
        return carPosition;
    }

    public void setPosition(final int position) {
        this.carPosition = position;
    }
}
