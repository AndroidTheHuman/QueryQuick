package FileHandlers;
import File.ExcelFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileSelector {
    private Scanner scanner = new Scanner(System.in);

    public List<ExcelFile> selectFiles() {
        List<ExcelFile> selectedFiles = new ArrayList<>();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Input Excel Files");
        fileChooser.setMultiSelectionEnabled(true);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
                System.out.print("Enter start row for " + file.getName() + ": ");
                int startRow = scanner.nextInt();
                System.out.print("Enter start column for " + file.getName() + ": ");
                int startColumn = scanner.nextInt();
                System.out.print("Enter end column for " + file.getName() + ": ");
                int endColumn = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                ExcelFile excelFile = new ExcelFile(file.getAbsolutePath(), startRow, startColumn, endColumn);
                selectedFiles.add(excelFile);
            }
        }

        frame.dispose();
        return selectedFiles;
    }
}



