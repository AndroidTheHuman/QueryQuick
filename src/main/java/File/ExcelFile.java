package File;

import java.io.File;

public class ExcelFile extends File {
    private int startRow;
    private int startColumn;
    private int endColumn;

    public ExcelFile(String pathname, int startRow, int startColumn, int endColumn) {
        super(pathname);
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endColumn = endColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }
}


