package utils;

import data.dependencies.LibrarianContract;
import domain.LibrarianModel;

public class TableCleaner {
    private static final LibrarianContract.Model model = new LibrarianModel();

    private TableCleaner() {}

    public static void clearTable(Class<?> entityClass) {
        String[][] data = model.getData(entityClass);

        for (String[] record : data) {
            try {
                int id = Integer.parseInt(record[0]);
                model.delete(id, entityClass);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
