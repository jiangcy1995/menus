package com.baizhi.service;

import com.baizhi.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
    List<Menu> findAll();

    void save(Menu menu);
    void save1(Menu menu);


    void update(Menu menu);

    Menu findOne(String id);

    void delete(String id);

    List<Menu> querySearch(String message);

    Map<String, Object> deleteAll();

    Map<String, Object> createIndex();


   /* List<Menu> queryAll();*/

    Menu getDetail(String id);

    Integer getCount();

    List<Menu> query(Integer currentPage,Integer size);
}
