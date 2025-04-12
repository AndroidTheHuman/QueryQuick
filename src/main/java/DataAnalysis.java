import FileHandlers.ExcelFileHandler;
import FileHandlers.FileSelector;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataAnalysis {
    public static void main(String[] args) throws IOException {
        FileSelector fileSelector = new FileSelector();
        List<File> inputFiles = fileSelector.selectFiles();

        if (!inputFiles.isEmpty()) {
            ExcelFileHandler excelFileHandler = new ExcelFileHandler();
            double totalSum = 0;
            int totalCount = 0;

            for (File file : inputFiles) {
                double average = excelFileHandler.calculateAverage(file);
                totalSum += average;
                totalCount++;
            }

            double overallAverage = totalSum / totalCount;

            JFileChooser outputDirChooser = new JFileChooser();
            outputDirChooser.setDialogTitle("Select Output Directory");
            outputDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int outputResult = outputDirChooser.showSaveDialog(null);
            if (outputResult == JFileChooser.APPROVE_OPTION) {
                File selectedOutputDir = outputDirChooser.getSelectedFile();
                excelFileHandler.writeAverageToFile(overallAverage, selectedOutputDir);
                System.out.println("Analysis complete. Overall average value: " + overallAverage);
            } else {
                System.out.println("Output directory selection cancelled.");
            }
        } else {
            System.out.println("No input files selected.");
        }
    }
}






