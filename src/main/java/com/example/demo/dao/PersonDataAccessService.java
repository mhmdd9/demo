package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAll() {
        final String sqlQuery = "select * from person";
        return jdbcTemplate.query(sqlQuery, (resultSet, i) -> {
            return new Person(UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("name"));
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sqlQuery = "select * from person where id = ?";
        Person person = jdbcTemplate.queryForObject(
                sqlQuery,
                new Object[]{id},
                (resultSet, i) -> {
                    return new Person(UUID.fromString(resultSet.getString("id")),
                            resultSet.getString("name"));
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }
}
