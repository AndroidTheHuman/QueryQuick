import FileHandlers.PivotTableCreator;
import File.ExcelFile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

public class PivotTableCreatorTest {

    @Test
    public void testPivotTableCreation() throws IOException {
        PivotTableCreator pivotTableCreator = new PivotTableCreator();
        List<Integer> columns = List.of(0, 1, 2); // Columns A, B, C
        ExcelFile file = new ExcelFile("\"C:\\Users\\xande\\OneDrive\\Dokumente\\Inputfilesexcel\\test1.xlsx\"", 0, columns);

        Workbook workbook = pivotTableCreator.createPivotTable(file);
        assertNotNull(workbook);
        assertEquals(1, workbook.getNumberOfSheets());
        assertEquals("Pivot Table", workbook.getSheetAt(0).getSheetName());
    }
}

