package me.xflyiwnl.iridbot.database.dao;

import com.wiring.api.entity.WiringResult;

import java.util.List;

public interface BotDAO<T> {

    void create();

    WiringResult get(int id);
    T get(WiringResult result);
    void insert(T object);
    void remove(T object);
    List<T> all();

}
