package FileHandlers;
import File.ExcelFile;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSelector {
    private Scanner scanner = new Scanner(System.in);

    public ExcelFile selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Input Excel File");

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int result = fileChooser.showOpenDialog(frame);
        ExcelFile excelFile = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            System.out.print("Enter start row for " + file.getName() + ": ");
            int startRow = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter column coordinates to query (comma-separated, e.g., A,B,AA): ");
            String columnsInput = scanner.nextLine();
            String[] columnsArray = columnsInput.split(",");
            List<Integer> columns = new ArrayList<>();
            for (String column : columnsArray) {
                columns.add(ExcelColumnConverter.columnToIndex(column.trim().toUpperCase()));
            }

            excelFile = new ExcelFile(file.getAbsolutePath(), startRow, columns);
        }

        frame.dispose();
        return excelFile;
    }
}





