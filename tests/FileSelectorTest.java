import File.ExcelFile;
import FileHandlers.ExcelColumnConverter;
import FileHandlers.FileSelector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class FileSelectorTest {

    @Test
    public void testColumnConversion() {
        assertEquals(0, ExcelColumnConverter.columnToIndex("A"));
        assertEquals(25, ExcelColumnConverter.columnToIndex("Z"));
        assertEquals(26, ExcelColumnConverter.columnToIndex("AA"));
        assertEquals(51, ExcelColumnConverter.columnToIndex("AZ"));
        assertEquals(52, ExcelColumnConverter.columnToIndex("BA"));
    }

    @Test
    public void testRowConversion() {
        FileSelector fileSelector = new FileSelector();
        // Simulate user input for row and column selection
        int startRow = 1; // Excel row number 1 should be converted to 0
        List<Integer> columns = List.of(ExcelColumnConverter.columnToIndex("A"), ExcelColumnConverter.columnToIndex("B"));
        ExcelFile file = new ExcelFile("\"C:\\Users\\xande\\OneDrive\\Dokumente\\Inputfilesexcel\\test1.xlsx\"", startRow - 1, columns);

        assertEquals(0, file.getStartRow());
        assertEquals(List.of(0, 1), file.getColumns());
    }
}

