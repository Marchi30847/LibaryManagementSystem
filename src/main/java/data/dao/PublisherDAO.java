package data.dao;

import data.annotations.Display;
import data.dependencies.DAO;
import data.entities.Publisher;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class PublisherDAO extends DAO<Publisher> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Publisher.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Publisher> getEntityClass() {
        return Publisher.class;
    }
}
