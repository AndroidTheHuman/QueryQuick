package FileHandlers;
import File.ExcelFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileHandler {
    public double calculateAverage(ExcelFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        double sum = 0;
        int count = 0;
        for (int i = file.getStartRow(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int j = file.getStartColumn(); j <= file.getEndColumn(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                        sum += cell.getNumericCellValue();
                        count++;
                    }
                }
            }
        }

        workbook.close();
        return sum / count;
    }

    public void writeResultsToFile(List<ExcelFile> files, File outputDir) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Results");

        int rowIndex = 0;
        for (ExcelFile file : files) {
            double average = calculateAverage(file);
            Row row = sheet.createRow(rowIndex++);
            Cell fileNameCell = row.createCell(0);
            fileNameCell.setCellValue(file.getName());
            Cell startRowCell = row.createCell(1);
            startRowCell.setCellValue(file.getStartRow());
            Cell startColumnCell = row.createCell(2);
            startColumnCell.setCellValue(file.getStartColumn());
            Cell endColumnCell = row.createCell(3);
            endColumnCell.setCellValue(file.getEndColumn());
            Cell averageCell = row.createCell(4);
            averageCell.setCellValue(average);
        }

        File outputFile = new File(outputDir, "results.xlsx");
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        workbook.write(outputStream);
        workbook.close();
    }
}



