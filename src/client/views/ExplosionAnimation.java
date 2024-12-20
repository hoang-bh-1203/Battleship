/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client.views;

/**
 *
 * @author bhhoa
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class ExplosionAnimation {

    private SquareView cell;
    private BoardView board;
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    private int currentIndex;

    public ExplosionAnimation(SquareView cell, BoardView board) {
        File[] icons = new File("assets/animation").listFiles();
        for (File file : icons) {
            try {
                String filename = file.getName();
                if (filename.startsWith("explosion")
                        && filename.endsWith(".png")) {
                    images.add(ImageIO.read(file));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.cell = cell;
        this.board = board;
        currentIndex = 0;
        cell.setExplosionImage(images.get(currentIndex));
        board.repaint();
    }

    public void start() {
        final Timer t = new Timer(10, null);
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                currentIndex++;
                if (currentIndex == images.size() + 25) {
                    cell.setExplosionImage(null);
                    t.stop();
                } else if (currentIndex < images.size()) {
                    cell.setExplosionImage(images.get(currentIndex));
                }
                board.repaint();
            }
        });
        t.start();
    }
}