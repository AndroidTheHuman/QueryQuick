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
    private String outputFileName;
    private boolean running = true;

    public void startUp() {
        System.out.println("Welcome to the Excel File Analyzer!");
        System.out.println("Type 'help' for a list of available commands.");
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
                System.out.println("Hello! How can I assist you today?");
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
                    promptForOutputFileName();
                    analyzeFiles();
                    running = true;
                    SwingUtilities.invokeLater(this::mainMenu);
                });
                break;
            case "list files":
                listFiles();
                break;
            case "help":
                printHelp();
                break;
            case "exit":
                running = false;
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Unknown command. Type 'help' for a list of available commands.");
                break;
        }
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  hello         - Greet the system.");
        System.out.println("  choose file   - Select an input Excel file.");
        System.out.println("  analyze       - Analyze the selected files and save results.");
        System.out.println("  list files    - Show the list of selected files and their order.");
        System.out.println("  help          - Display this help message.");
        System.out.println("  exit          - Exit the application.");
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

    private void promptForOutputFileName() {
        System.out.print("Enter the name for the output file (without extension): ");
        outputFileName = scanner.nextLine();
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
        if (outputFileName == null || outputFileName.trim().isEmpty()) {
            System.out.println("No output file name provided. Use 'analyze' command to provide output file name.");
            return;
        }

        System.out.println("Starting analysis...");
        ExcelFileHandler excelFileHandler = new ExcelFileHandler();
        try {
            excelFileHandler.writeResultsToFile(inputFiles, outputDirectory, outputFileName);
            System.out.println("Analysis complete. Results saved to: " + outputDirectory.getAbsolutePath() + "/" + outputFileName + ".xlsx");
        } catch (IOException e) {
            System.out.println("An error occurred during analysis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void listFiles() {
        if (inputFiles.isEmpty()) {
            System.out.println("No files selected.");
        } else {
            System.out.println("Selected files:");
            for (int i = 0; i < inputFiles.size(); i++) {
                ExcelFile file = inputFiles.get(i);
                System.out.println((i + 1) + ". " + file.getName());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CommandLineInterface cli = new CommandLineInterface();
            cli.startUp();
        });
    }
}














