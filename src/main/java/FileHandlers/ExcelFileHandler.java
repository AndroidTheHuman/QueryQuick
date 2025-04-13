package FileHandlers;
import File.ExcelFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileHandler {
    private PivotTableCreator pivotTableCreator = new PivotTableCreator();

    public void writeResultsToFile(List<ExcelFile> files, File outputDir) throws IOException {
        System.out.println("Writing results to file...");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Results");

        int rowIndex = 0;
        for (ExcelFile file : files) {
            System.out.println("Processing file: " + file.getName());
            Workbook pivotWorkbook = pivotTableCreator.createPivotTable(file);
            Sheet pivotSheet = pivotWorkbook.getSheetAt(0);

            for (int i = 0; i <= pivotSheet.getLastRowNum(); i++) {
                Row pivotRow = pivotSheet.getRow(i);
                Row row = sheet.createRow(rowIndex++);
                for (int j = 0; j < pivotRow.getLastCellNum(); j++) {
                    Cell pivotCell = pivotRow.getCell(j);
                    Cell cell = row.createCell(j);
                    if (pivotCell != null) {
                        switch (pivotCell.getCellType()) {
                            case NUMERIC:
                                cell.setCellValue(pivotCell.getNumericCellValue());
                                break;
                            case STRING:
                                cell.setCellValue(pivotCell.getStringCellValue());
                                break;
                            case BOOLEAN:
                                cell.setCellValue(pivotCell.getBooleanCellValue());
                                break;
                            default:
                                cell.setCellValue(pivotCell.toString());
                                break;
                        }
                    }
                }
            }
        }

        File outputFile = new File(outputDir, "results3.xlsx");
        System.out.println("Saving results to: " + outputFile.getAbsolutePath());
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        workbook.write(outputStream);
        workbook.close();
        System.out.println("File saved successfully.");
    }
}






