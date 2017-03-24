package com.hotdog.springboot.inf;

/**
 * Created by hotdog on 2017/3/24.
 */
public interface IBaseService<Entity, T> {
    public int insert(Entity entity);

    public int update(Entity entity);

    public int delete(Integer id);

    public Entity queryById(T t);
}
