package data.dao;

import data.annotations.Display;
import data.dependencies.DAO;
import data.entities.Member;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class MemberDAO extends DAO<Member> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Member.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Member> getEntityClass() {
        return Member.class;
    }
}
