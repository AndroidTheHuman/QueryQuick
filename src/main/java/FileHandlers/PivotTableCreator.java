package FileHandlers;

import File.ExcelFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class PivotTableCreator {
    public Workbook createPivotTable(ExcelFile file) throws IOException {
        System.out.println("Creating pivot table for file: " + file.getName());
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        Workbook pivotWorkbook = new XSSFWorkbook();
        Sheet pivotSheet = pivotWorkbook.createSheet("Pivot Table");

        Map<String, CellRangeAddress> mergedRegions = cacheMergedRegions(sheet);

        int rowIndex = 0;
        for (int i = file.getStartRow(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Row pivotRow = pivotSheet.createRow(rowIndex++);
                int pivotColumnIndex = 0;
                for (int column : file.getColumns()) {
                    Cell cell = row.getCell(column);
                    if (cell != null) {
                        String cellKey = cell.getRowIndex() + "-" + cell.getColumnIndex();
                        if (mergedRegions.containsKey(cellKey)) {
                            CellRangeAddress mergedRegion = mergedRegions.get(cellKey);
                            Cell mergedCell = sheet.getRow(mergedRegion.getFirstRow()).getCell(mergedRegion.getFirstColumn());
                            Cell pivotCell = pivotRow.createCell(pivotColumnIndex++);
                            copyCellValue(mergedCell, pivotCell);
                            column += mergedRegion.getNumberOfCells() - 1;
                        } else {
                            Cell pivotCell = pivotRow.createCell(pivotColumnIndex++);
                            copyCellValue(cell, pivotCell);
                        }
                    }
                }
            }
        }

        workbook.close();
        System.out.println("Pivot table created successfully for file: " + file.getName());
        return pivotWorkbook;
    }

    private Map<String, CellRangeAddress> cacheMergedRegions(Sheet sheet) {
        Map<String, CellRangeAddress> mergedRegions = new HashMap<>();
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
            for (int row = mergedRegion.getFirstRow(); row <= mergedRegion.getLastRow(); row++) {
                for (int col = mergedRegion.getFirstColumn(); col <= mergedRegion.getLastColumn(); col++) {
                    mergedRegions.put(row + "-" + col, mergedRegion);
                }
            }
        }
        return mergedRegions;
    }

    private void copyCellValue(Cell sourceCell, Cell targetCell) {
        switch (sourceCell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(sourceCell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dateStr = dateFormat.format(sourceCell.getDateCellValue());
                    targetCell.setCellValue(dateStr);
                } else {
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                }
                break;
            case STRING:
                targetCell.setCellValue(sourceCell.getStringCellValue());
                break;
            case BOOLEAN:
                targetCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            default:
                targetCell.setCellValue(sourceCell.toString());
                break;
        }
    }
}














