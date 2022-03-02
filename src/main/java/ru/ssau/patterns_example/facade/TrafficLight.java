package ru.ssau.patterns_example.facade;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TrafficLight {

    private static final String TRAFFIC_LIGHT_RED_PATH = "src/main/resources/r.png";
    private static final String TRAFFIC_LIGHT_GREEN_PATH = "src/main/resources/g.png";
    private static final String TRAFFIC_LIGHT_YELLOW_PATH = "src/main/resources/y.png";
    private static final String TRAFFIC_LIGHT_RED_YELLOW_PATH = "src/main/resources/r-y.png";

    private volatile State state = State.GREEN;

    public TrafficLight() {
    }

    public BufferedImage getCurrentTLImage() throws IOException {
        switch (state) {
            case GREEN:
                return ImageIO.read(new File(TRAFFIC_LIGHT_GREEN_PATH));
            case READ:
                return ImageIO.read(new File(TRAFFIC_LIGHT_RED_PATH));
            case YELLOW:
                return ImageIO.read(new File(TRAFFIC_LIGHT_YELLOW_PATH));
            case YELLOW_RED:
                return ImageIO.read(new File(TRAFFIC_LIGHT_RED_YELLOW_PATH));
        }
        return null;
    }

    public State getState() {
        return this.state;
    }

    public void changeState() throws InterruptedException {
        switch (state) {
            case GREEN:
                Thread.sleep(10000);
                this.state = State.YELLOW;
                break;
            case READ:
                Thread.sleep(10000);
                this.state = State.YELLOW_RED;
                break;
            case YELLOW:
                Thread.sleep(2000);
                this.state = State.READ;
                break;
            case YELLOW_RED:
                Thread.sleep(2000);
                this.state = State.GREEN;
                break;
        }
    }

    public enum State {
        GREEN, READ, YELLOW, YELLOW_RED
    }
}
