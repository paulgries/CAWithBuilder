package app;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addChangePasswordUseCase()
                .build();

        application.pack();
        application.setVisible(true);
//        CardPanelBuilder cardPanelBuilder = new CardPanelBuilder().addLoginView();

    }

    private static void addMainPanel(JFrame application) {
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final CardLayout cardLayout = new CardLayout();

        // Contains the various View instances. Only one view is visible at a time.
        final JPanel mainScreen = new JPanel(cardLayout);
        application.add(mainScreen);
    }

    private static Component getCardLayout(JFrame application) {
        return application.getContentPane().getComponent(0);
    }
}
