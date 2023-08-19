package me.xflyiwnl.iridbot;

import api.longpoll.bots.exceptions.VkApiException;
import me.xflyiwnl.iridbot.database.Database;
import me.xflyiwnl.iridbot.longpoll.Lonpoll;
import me.xflyiwnl.iridbot.message.TestMessage;
import me.xflyiwnl.iridbot.object.User;
import me.xflyiwnl.iridbot.object.VkMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static Main instance;

    private Database database;
    private Lonpoll bot;

    private HashMap<String, VkMessage> messages = new HashMap<String, VkMessage>();
    private List<User> users = new ArrayList<User>();

    private String token;

    public void start() {
        instance = this;

        messages();

        database = new Database("localhost", "root", "1234", 3306);
        database.loadAll();
        token = "token";
        bot = new Lonpoll(token);
        poll();

    }

    public void messages() {
        Main.getInstance().getMessages().put("/test", new TestMessage(null, ""));
    }

    public void poll() {
        try {
            bot.startPolling();
        } catch (VkApiException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Database getDatabase() {
        return database;
    }

    public HashMap<String, VkMessage> getMessages() {
        return messages;
    }

    public Lonpoll getBot() {
        return bot;
    }

    public String getToken() {
        return token;
    }

    public List<User> getUsers() {
        return users;
    }

    public static Main getInstance() {
        return instance;
    }

}
