package com.mruruc.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<Entity,ID> {
    Entity save(Entity entity) ;
    ID delete(ID entityId);
    Entity update(ID id, Entity entity);
    Optional<Entity> findById(ID entityId);
    List<Entity> findAll();
}
