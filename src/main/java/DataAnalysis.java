import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataAnalysis {
    public static void main(String[] args) throws IOException {
        // Create a JFrame to hold the file chooser
        JFrame frame = new JFrame("File Chooser Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create a file chooser for input file
        JFileChooser inputFileChooser = new JFileChooser();
        inputFileChooser.setDialogTitle("Select Input Excel File");

        // Show the file chooser dialog for input file
        int inputResult = inputFileChooser.showOpenDialog(frame);

        // Check if an input file was selected
        if (inputResult == JFileChooser.APPROVE_OPTION) {
            // Get the selected input file
            File selectedInputFile = inputFileChooser.getSelectedFile();
            System.out.println("Selected input file: " + selectedInputFile.getAbsolutePath());

            // Perform data analysis on the selected input file
            Workbook inputWorkbook = WorkbookFactory.create(selectedInputFile);
            Sheet inputSheet = inputWorkbook.getSheetAt(0);

            double sum = 0;
            int count = 0;
            for (Row row : inputSheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    sum += cell.getNumericCellValue();
                    count++;
                }
            }

            double average = sum / count;

            Workbook outputWorkbook = new XSSFWorkbook();
            Sheet outputSheet = outputWorkbook.createSheet("Average");
            Row row = outputSheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(average);

            // Create a file chooser for output directory
            JFileChooser outputDirChooser = new JFileChooser();
            outputDirChooser.setDialogTitle("Select Output Directory");
            outputDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // Show the file chooser dialog for output directory
            int outputResult = outputDirChooser.showSaveDialog(frame);

            // Check if an output directory was selected
            if (outputResult == JFileChooser.APPROVE_OPTION) {
                // Get the selected output directory
                File selectedOutputDir = outputDirChooser.getSelectedFile();
                System.out.println("Selected output directory: " + selectedOutputDir.getAbsolutePath());

                // Create the output file in the selected directory
                File outputFile = new File(selectedOutputDir, "output.xlsx");

                FileOutputStream outputStream = new FileOutputStream(outputFile);
                outputWorkbook.write(outputStream);
                outputWorkbook.close();

                System.out.println("Analysis complete. Average value: " + average);
            } else {
                System.out.println("Output directory selection cancelled.");
            }
        } else {
            System.out.println("Input file selection cancelled.");
        }

        // Dispose the frame
        frame.dispose();
    }
}





