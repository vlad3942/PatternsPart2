package ru.ssau.patterns_example.facade;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FacadeOfProcess extends AbstractFacadeOfProcess {

    private static final String ROAD_IMAGE_PATH = "src/main/resources/road.png";
    private static final String CAR_PATH = "src/main/resources/car.png";
    private static final String TRAFFIC_LIGHT_RED_PATH = "src/main/resources/r.png";
    private static final String TRAFFIC_LIGHT_GREEN_PATH = "src/main/resources/g.png";
    private static final String TRAFFIC_LIGHT_YELLOW_PATH = "src/main/resources/y.png";
    private static final String TRAFFIC_LIGHT_RED_YELLOW_PATH = "src/main/resources/r-y.png";

    private static BufferedImage carPicture;
    private static BufferedImage tlPicture;
//    private static BufferedImage roadPicture;
    private BufferedImage roadPicture;
    private JPanel jPanel;
    private JFrame jFrame;

    private TrafficLight trafficLight;
    private Car car;

    public FacadeOfProcess() {
    }

    @Override
    public void runProcess() {
        init();
        runCarMoving();
        tlSwitcher();
    }

/*
    public static void main(String[] args) throws IOException {

        roadPicture = ImageIO.read(new File(ROAD_IMAGE_PATH));
        carPicture = ImageIO.read(new File(CAR_PATH));
        tlPicture = ImageIO.read(new File(TRAFFIC_LIGHT_GREEN_PATH));


        Graphics2D g = (Graphics2D) roadPicture.getGraphics();
        //g.setStroke(new BasicStroke(3));
        //g.setColor(Color.BLUE);
        //g.drawRect(10, 10, roadPicture.getWidth() - 20, roadPicture.getHeight() - 20);
        //draw traffic light
        g.drawImage(tlPicture, null, 150, 120);
        //draw car
        g.drawImage(carPicture, null, -80, 200);

        JLabel picLabel = new JLabel(new ImageIcon(roadPicture));

        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);

        JFrame f = new JFrame();
        f.setSize(new Dimension(roadPicture.getWidth() + 100, roadPicture.getHeight() + 100));
        f.add(jPanel);
        f.setVisible(true);

        Thread thread = new Thread(() -> {
            int carPosition = -80;
            while (true) {
                Graphics2D gr = newGr();
                gr.drawImage(carPicture, null, carPosition, 200);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (carPosition < 380)
                    carPosition += 20;
                else
                    carPosition = -80;
                JLabel pL = new JLabel(new ImageIcon(roadPicture));

                jPanel.removeAll();

                jPanel.add(pL);
                f.repaint();
                f.revalidate();
                f.pack();
                f.setLocationRelativeTo(null);
            }
        });
        thread.start();
    }

    public static Graphics2D newGr() {
        try {
            roadPicture = ImageIO.read(new File(ROAD_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics2D g = (Graphics2D) roadPicture.getGraphics();
        g.drawImage(tlPicture, null, 150, 120);
        return g;
    }*/

    private void runCarMoving() {
        Thread thread = new Thread(() -> {
            while (true) {
                Graphics2D gr = getRoadAndTLGraphics();
                gr.drawImage(car.getPicture(), null, car.getPosition(), 200);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (
                        car.getPosition() < 40
                        || car.getPosition() > 60
                        || TrafficLight.State.GREEN.equals(trafficLight.getState())
                        || TrafficLight.State.YELLOW.equals(trafficLight.getState())
                ) {
                    if (car.getPosition() < 380)
                        car.setPosition(car.getPosition() + 20);
                    else
                        car.setPosition(-80);
                }
                JLabel pL = new JLabel(new ImageIcon(roadPicture));

                jPanel.removeAll();

                jPanel.add(pL);
                jFrame.repaint();
                jFrame.revalidate();
                jFrame.pack();
                jFrame.setLocationRelativeTo(null);
            }
        });
        thread.start();
    }

    private Graphics2D getRoadAndTLGraphics() {
        BufferedImage tlImage = null;
        try {
            roadPicture = ImageIO.read(new File(ROAD_IMAGE_PATH));
            tlImage = trafficLight.getCurrentTLImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics2D g = (Graphics2D) roadPicture.getGraphics();
        g.drawImage(tlImage, null, 150, 119);
        return g;
    }

    private void tlSwitcher() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    trafficLight.changeState();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void init() {
        this.car = new Car();
        this.trafficLight = new TrafficLight();

        try {
            this.roadPicture = ImageIO.read(new File(ROAD_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics2D g = (Graphics2D) roadPicture.getGraphics();
        try {
            g.drawImage(trafficLight.getCurrentTLImage(), null, 150, 119);
            g.drawImage(car.getPicture(), null, -80, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(roadPicture));

        jPanel = new JPanel();
        jPanel.add(picLabel);

        jFrame = new JFrame();
        jFrame.setSize(new Dimension(roadPicture.getWidth() + 100, roadPicture.getHeight() + 100));
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }
}
