package data.dao;

import data.annotations.Display;
import data.dependencies.DAO;
import data.entities.Borrowing;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class BorrowingDAO extends DAO<Borrowing> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Borrowing.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Borrowing> getEntityClass() {
        return Borrowing.class;
    }
}
