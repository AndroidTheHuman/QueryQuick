package cli;
import File.ExcelFile;
import FileHandlers.ExcelFileHandler;
import FileHandlers.FileSelector;
import FileHandlers.DirectorySelector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class CommandLineInterface {
    private Scanner scanner = new Scanner(System.in);
    private List<ExcelFile> inputFiles = new ArrayList<>();
    private File outputDirectory;
    private boolean running = true;

    public void startUp() {
        System.out.println("Hello!");
        mainMenu();
    }

    private void mainMenu() {
        while (running) {
            System.out.print(">");
            String input = scanner.nextLine();
            handleCommand(input);
        }
    }

    private void handleCommand(String input) {
        switch (input.toLowerCase()) {
            case "hello":
                System.out.println("Hello 2!");
                break;
            case "choose files":
                running = false;
                SwingUtilities.invokeLater(() -> {
                    chooseFiles();
                    running = true;
                    SwingUtilities.invokeLater(this::mainMenu);
                });
                break;
            case "choose output directory":
                running = false;
                SwingUtilities.invokeLater(() -> {
                    chooseOutputDirectory();
                    running = true;
                    SwingUtilities.invokeLater(this::mainMenu);
                });
                break;
            case "analyze":
                analyzeFiles();
                break;
            default:
                System.out.println("Unknown command. Available commands: hello, choose files, choose output directory, analyze");
                break;
        }
    }

    private void chooseFiles() {
        FileSelector fileSelector = new FileSelector();
        inputFiles = fileSelector.selectFiles();
        System.out.println("Files selected: " + inputFiles.size());
    }

    private void chooseOutputDirectory() {
        DirectorySelector directorySelector = new DirectorySelector();
        outputDirectory = directorySelector.selectDirectory();
        if (outputDirectory != null) {
            System.out.println("Output directory selected: " + outputDirectory.getAbsolutePath());
        } else {
            System.out.println("Output directory selection cancelled.");
        }
    }

    private void analyzeFiles() {
        if (inputFiles.isEmpty()) {
            System.out.println("No input files selected. Use 'choose files' command first.");
            return;
        }
        if (outputDirectory == null) {
            System.out.println("No output directory selected. Use 'choose output directory' command first.");
            return;
        }

        ExcelFileHandler excelFileHandler = new ExcelFileHandler();
        try {
            excelFileHandler.writeResultsToFile(inputFiles, outputDirectory);
            System.out.println("Analysis complete. Results saved to: " + outputDirectory.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred during analysis: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CommandLineInterface cli = new CommandLineInterface();
            cli.startUp();
        });
    }
}







