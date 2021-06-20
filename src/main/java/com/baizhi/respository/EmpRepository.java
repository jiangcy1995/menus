package com.baizhi.respository;

import com.baizhi.entity.Menu;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

//创建接口集成Es的接口  参数:<操作对象类型,序列化主键的类型>
public interface EmpRepository extends ElasticsearchRepository<Menu,String> {
}
