package FileHandlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileHandler {
    public double calculateAverage(File file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        double sum = 0;
        int count = 0;
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                sum += cell.getNumericCellValue();
                count++;
            }
        }

        workbook.close();
        return sum / count;
    }

    public void writeAverageToFile(double average, File outputDir) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Average");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(average);

        File outputFile = new File(outputDir, "output.xlsx");
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        workbook.write(outputStream);
        workbook.close();
    }
}

