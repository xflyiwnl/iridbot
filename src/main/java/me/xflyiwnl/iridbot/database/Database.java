package me.xflyiwnl.iridbot.database;

import com.wiring.api.WiringAPI;
import me.xflyiwnl.iridbot.Main;
import me.xflyiwnl.iridbot.database.dao.UserDAO;

public class Database {

    private WiringAPI api;
    private String database;

    private UserDAO userDAO;

    public Database(String host, String user, String password, int port) {
        init(host, user, password, port);
    }

    public void init(String host, String user, String password, int port) {
        api = new WiringAPI(host, port, user, password, null);
        database = "s1491_database";

        userDAO = new UserDAO(api, database);
        userDAO.create();
    }

    public void loadAll() {
        Main.getInstance().getUsers().addAll(userDAO.all());
    }

    public WiringAPI getApi() {
        return api;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
