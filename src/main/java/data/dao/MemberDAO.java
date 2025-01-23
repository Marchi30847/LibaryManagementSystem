package data.dao;

import data.annotations.Display;
import data.entities.Member;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Data Access Object (DAO) for managing {@link Member} entities.
 * Extends the generic {@link DAO} class to perform CRUD operations on Member entities.
 */
public class MemberDAO extends DAO<Member> {
    //todo добавить чеки мейла
    /**
     * Retrieves the column names for the Member entity.
     * This method uses reflection to find fields that are annotated with {@link Display}.
     * Only the fields with the {@link Display} annotation will be returned as column names.
     *
     * @return A list of column names for the Member entity.
     */
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Member.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    /**
     * Returns the class type of the Member entity.
     *
     * @return The class type of the Member entity.
     */
    @Override
    protected Class<Member> getEntityClass() {
        return Member.class;
    }
}