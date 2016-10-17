package me.ecmephi.task.service.dao;

import me.ecmephi.task.service.entity.User;

import java.util.List;

public class UserDAO extends BaseDAO<User, Long> {

    public void create(User entity) {
        entityManager.persist(entity);
    }

    public List<User> getAll() {
        return getAll(User.class);
    }

    public User getById(Long id) {
        if (id == null)
            return null;
        return entityManager.find(User.class, id);
    }

    public User remove(Long id) {
        return remove(User.class, id);
    }

    public void update(User entity) {
        entityManager.merge(entity);
    }

    public User getByUsername(String username) {
        List<User> possibleMatches = entityManager
                .createNamedQuery("User.getByUsername()", User.class)
                .setParameter("name", username)
                .getResultList();
        return possibleMatches.size() != 0 ? possibleMatches.get(0) : null;
    }


}
