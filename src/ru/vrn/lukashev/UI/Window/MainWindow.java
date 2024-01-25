package ru.vrn.lukashev.UI.Window;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        ;

        // Устанавливаем размер окна на весь экран
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        // Устанавливаем операцию закрытия окна по умолчанию
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.getWidth();
//
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        System.out.println(width + " " + height);
        MainPanel mainPanel = new MainPanel((int)width, (int)height);
        this.add(mainPanel);
        // Делаем окно видимым
        this.setVisible(true);
    }
}
