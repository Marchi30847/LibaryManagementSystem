package data.dao;

import data.dependencies.DAO;
import data.entities.Member;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class MemberDAO extends DAO<Member> {
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Member.class.getDeclaredFields())
                .map(Field::getName)
                .toList();
    }

    @Override
    protected Class<Member> getEntityClass() {
        return Member.class;
    }
}
