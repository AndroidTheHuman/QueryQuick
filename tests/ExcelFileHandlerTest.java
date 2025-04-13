import FileHandlers.ExcelFileHandler;
import File.ExcelFile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExcelFileHandlerTest {

    @Test
    public void testOutputFileNaming() throws IOException {
        ExcelFileHandler excelFileHandler = new ExcelFileHandler();
        List<ExcelFile> files = List.of(new ExcelFile("\"C:\\Users\\xande\\OneDrive\\Dokumente\\Inputfilesexcel\\test1.xlsx\"", 0, List.of(0, 1)),
                new ExcelFile("\"C:\\Users\\xande\\OneDrive\\Dokumente\\Inputfilesexcel\\test2.xlsx\"", 0, List.of(2, 3)));
        File outputDir = new File("C:\\Users\\xande\\OneDrive\\Dokumente\\Outputfilesexcel");
        String outputFileName = "testOutput";

        excelFileHandler.writeResultsToFile(files, outputDir, outputFileName);
        File outputFile = new File(outputDir, outputFileName + ".xlsx");
        assertTrue(outputFile.exists());
    }
}

