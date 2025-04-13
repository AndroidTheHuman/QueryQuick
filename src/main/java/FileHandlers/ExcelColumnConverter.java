package FileHandlers;

public class ExcelColumnConverter {
    public static int columnToIndex(String column) {
        int index = 0;
        for (int i = 0; i < column.length(); i++) {
            index = index * 26 + (column.charAt(i) - 'A' + 1);
        }
        return index - 1; // A correspondeert met 1 maar ik heb A = 0 nodig
    }
}

