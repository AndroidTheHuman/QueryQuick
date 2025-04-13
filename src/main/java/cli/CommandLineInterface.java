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
            case "choose file":
                running = false;
                SwingUtilities.invokeLater(() -> {
                    chooseFile();
                    running = true;
                    SwingUtilities.invokeLater(this::mainMenu);
                });
                break;
            case "analyze":
                running = false;
                SwingUtilities.invokeLater(() -> {
                    chooseOutputDirectory();
                    analyzeFiles();
                    running = true;
                    SwingUtilities.invokeLater(this::mainMenu);
                });
                break;
            default:
                System.out.println("Unknown command. Available commands: hello, choose file, analyze");
                break;
        }
    }

    private void chooseFile() {
        FileSelector fileSelector = new FileSelector();
        ExcelFile file = fileSelector.selectFile();
        if (file != null) {
            inputFiles.add(file);
            System.out.println("File selected: " + file.getName());
        } else {
            System.out.println("File selection cancelled.");
        }
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
            System.out.println("No input files selected. Use 'choose file' command first.");
            return;
        }
        if (outputDirectory == null) {
            System.out.println("No output directory selected. Use 'analyze' command to select output directory.");
            return;
        }

        System.out.println("Starting analysis...");
        ExcelFileHandler excelFileHandler = new ExcelFileHandler();
        try {
            excelFileHandler.writeResultsToFile(inputFiles, outputDirectory);
            System.out.println("Analysis complete. Results saved to: " + outputDirectory.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred during analysis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CommandLineInterface cli = new CommandLineInterface();
            cli.startUp();
        });
    }
}










