package FileHandlers;

import File.ExcelFile;

import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PivotTableCreator {
    public Workbook createPivotTable(ExcelFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        Workbook pivotWorkbook = new XSSFWorkbook();
        Sheet pivotSheet = pivotWorkbook.createSheet("Pivot Table");

        int rowIndex = 0;
        for (int i = file.getStartRow(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Row pivotRow = pivotSheet.createRow(rowIndex++);
                for (int j = file.getStartColumn(); j <= file.getEndColumn(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        Cell pivotCell = pivotRow.createCell(j - file.getStartColumn());
                        switch (cell.getCellType()) {
                            case NUMERIC:
                                pivotCell.setCellValue(cell.getNumericCellValue());
                                break;
                            case STRING:
                                pivotCell.setCellValue(cell.getStringCellValue());
                                break;
                            case BOOLEAN:
                                pivotCell.setCellValue(cell.getBooleanCellValue());
                                break;
                            default:
                                pivotCell.setCellValue(cell.toString());
                                break;
                        }
                    }
                }
            }
        }

        workbook.close();
        return pivotWorkbook;
    }
}

