package ui;

import bootstrap.InteractorPool;

import javax.swing.*;

public class ScreenController {
    private final MainScreen mainScreen;
    private final InteractorPool interactorPool;

    public ScreenController(InteractorPool interactorPool) {
        this.interactorPool = interactorPool;
        this.mainScreen = new MainScreen(this);
    }

    public InteractorPool getInteractorPool() {
        return interactorPool;
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public void open(JPanel panel) {
        mainScreen.setContentPane(panel);
        mainScreen.revalidate();
        mainScreen.repaint();
    }
}
