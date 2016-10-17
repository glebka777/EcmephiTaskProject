package me.ecmephi.task.service.dao;

import me.ecmephi.task.service.entity.Record;

import java.util.List;

public class LogDAO extends BaseDAO<Record, Long> {

    public void create(Record entity) {
        entityManager.persist(entity);
    }

    public List<Record> getAll() {
        return getAll(Record.class);
    }

    public Record getById(Long id) {
        return entityManager.find(Record.class, id);
    }

    public Record remove(Long id) {
        return remove(Record.class, id);
    }

    public void update(Record entity) {
        entityManager.merge(entity);
    }

}