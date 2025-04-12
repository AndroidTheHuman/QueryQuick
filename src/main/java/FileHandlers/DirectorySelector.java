package FileHandlers;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class DirectorySelector {
    public File selectDirectory() {
        JFileChooser dirChooser = new JFileChooser();
        dirChooser.setDialogTitle("Select Output Directory");
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int result = dirChooser.showSaveDialog(frame);
        File selectedDirectory = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedDirectory = dirChooser.getSelectedFile();
        }

        frame.dispose();
        return selectedDirectory;
    }
}
