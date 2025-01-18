package data.dao;

import data.dependencies.DAO;
import data.entities.Borrowing;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class BorrowingDAO extends DAO<Borrowing> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Borrowing.class.getDeclaredFields())
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Borrowing> getEntityClass() {
        return Borrowing.class;
    }
}
