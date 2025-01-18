package data.dao;

import data.annotations.Display;
import data.dependencies.DAO;
import data.entities.Copy;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CopyDAO extends DAO<Copy> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Copy.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Copy> getEntityClass() {
        return Copy.class;
    }
}
