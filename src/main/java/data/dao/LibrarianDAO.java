package data.dao;

import data.annotations.Display;
import data.dependencies.DAO;
import data.entities.Librarian;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class LibrarianDAO extends DAO<Librarian> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Librarian.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Librarian> getEntityClass() {
        return Librarian.class;
    }
}
