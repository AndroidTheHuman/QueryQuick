package FileHandlers;
import File.ExcelFile;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
            System.out.print("Enter start column for " + file.getName() + ": ");
            int startColumn = scanner.nextInt();
            System.out.print("Enter end column for " + file.getName() + ": ");
            int endColumn = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            excelFile = new ExcelFile(file.getAbsolutePath(), startRow, startColumn, endColumn);
        }

        frame.dispose();
        return excelFile;
    }
}



