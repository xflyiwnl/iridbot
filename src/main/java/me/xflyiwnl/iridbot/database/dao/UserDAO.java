package me.xflyiwnl.iridbot.database.dao;

import com.wiring.api.WiringAPI;
import com.wiring.api.entity.Column;
import com.wiring.api.entity.ColumnType;
import com.wiring.api.entity.WiringResult;
import me.xflyiwnl.iridbot.object.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements BotDAO<User> {

    private WiringAPI api;
    private String database;

    public UserDAO(WiringAPI api, String database) {
        this.api = api;
        this.database = database;
    }

    @Override
    public void create() {
        api.getDatabase(database)
                .createTable("users")
                .column(new Column("id", ColumnType.VARCHAR).notNull().primaryKey())
                .column(new Column("irids", ColumnType.VARCHAR).notNull())
                .execute();
    }

    @Override
    public WiringResult get(int id) {
        return api.select(database)
                .table("users")
                .value(id)
                .execute();
    }

    @Override
    public User get(WiringResult result) {
        return new User(
                Integer.parseInt(result.get("id").toString()),
                Double.parseDouble(result.get("irids").toString())
        );
    }

    @Override
    public void insert(User object) {
        api.insert(database)
                .table("users")
                .column("id", object.getId())
                .column("irids", object.getIrids())
                .execute();
    }

    @Override
    public void remove(User object) {
        api.delete(database)
                .value(object.getId())
                .execute();
    }

    @Override
    public List<User> all() {
        List<User> users = new ArrayList<User>();
        List<WiringResult> results = api.selectAll(database)
                .table("users")
                .execute();
        for (WiringResult result : results) {
            users.add(get(result));
        }
        return users;
    }

}
