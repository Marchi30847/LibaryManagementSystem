package data.dao;

import data.dependencies.DAO;
import data.entities.Book;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class BookDAO extends DAO<Book> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Book.class.getDeclaredFields())
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
