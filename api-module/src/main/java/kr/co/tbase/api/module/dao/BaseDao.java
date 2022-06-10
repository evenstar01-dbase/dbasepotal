package kr.co.tbase.api.module.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao<T> {
    @Autowired
    SqlSessionTemplate mybatisSession;

    @Autowired
    T jpaRepository;

    public SqlSessionTemplate mybatis(){
        return mybatisSession;
    }

    public T jpa(){
        return jpaRepository;
    }
}
