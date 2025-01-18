package data.dao;

import data.annotations.Display;
import data.dependencies.DAO;
import data.entities.Book;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class BookDAO extends DAO<Book> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Book.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
