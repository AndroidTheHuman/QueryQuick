package FileHandlers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileSelector {
    private List<File> selectedFiles;

    public FileSelector() {
        selectedFiles = new ArrayList<>();
    }

    public List<File> selectFiles() {
        JFrame frame = new JFrame("File Chooser Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Input Excel Files");
        fileChooser.setMultiSelectionEnabled(true);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
                selectedFiles.add(file);
            }
        }

        frame.dispose();
        return selectedFiles;
    }
}

