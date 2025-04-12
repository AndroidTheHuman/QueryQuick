import cli.CommandLineInterface;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CommandLineInterface cli = new CommandLineInterface();
            cli.startUp();
        });
    }
}
