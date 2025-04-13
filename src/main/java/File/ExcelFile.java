package File;

import java.io.File;
import java.util.List;

public class ExcelFile extends File {
    private int startRow;
    private List<Integer> columns;

    public ExcelFile(String pathname, int startRow, List<Integer> columns) {
        super(pathname);
        this.startRow = startRow;
        this.columns = columns;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public List<Integer> getColumns() {
        return columns;
    }

    public void setColumns(List<Integer> columns) {
        this.columns = columns;
    }
}



