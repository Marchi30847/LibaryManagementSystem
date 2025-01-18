package data.dao;

import data.dependencies.DAO;
import data.entities.Publisher;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class PublisherDAO extends DAO<Publisher> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Publisher.class.getDeclaredFields())
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Publisher> getEntityClass() {
        return Publisher.class;
    }
}
