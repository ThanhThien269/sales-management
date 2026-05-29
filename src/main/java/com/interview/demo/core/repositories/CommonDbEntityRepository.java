package com.interview.demo.core.repositories;

import java.util.List;

public interface CommonDbEntityRepository<DbEntity, ID> {
    DbEntity save(DbEntity entity);
    List<DbEntity> saveAll(Iterable<DbEntity> entities);
    DbEntity findById(ID id);
    List<DbEntity> findByIds(Iterable<ID> ids);
    List<DbEntity> findAll();
    void deleteById(ID id);
    void delete(DbEntity entity);
    void deleteByIds(Iterable<ID> ids);
    boolean existsById(ID id);

    void flush();
}
