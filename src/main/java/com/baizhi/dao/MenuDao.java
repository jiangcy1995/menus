package com.baizhi.dao;

import com.baizhi.entity.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> findAll();

    void save(Menu menu);

    void update(Menu menu);

    Menu findOne(String id);

    void delete(String id);
}
