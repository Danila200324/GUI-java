package kong;

import kong.ui.KongFrame;

import javax.swing.*;

public class KongMain {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1");
        SwingUtilities.invokeLater(() -> {
            new KongFrame().setVisible(true);
        });
    }
}
