package com.company;

public interface DAO<Entity, Key> {
    boolean create(Entity object);
    Entity read(Key key);
    boolean update(Entity object);
    boolean delete(Entity object);
    String getTableView(String data);
}
