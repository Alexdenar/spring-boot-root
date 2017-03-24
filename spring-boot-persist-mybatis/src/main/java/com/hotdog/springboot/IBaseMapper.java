package com.hotdog.springboot;

/**
 * Created by hotdog on 2017/3/24.
 */
public interface IBaseMapper<Entity, T> {
    public int insert(Entity entity);

    public int update(Entity entity);

    public int delete(Integer id);

    public Entity queryById(T t);
}
