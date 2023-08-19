package me.xflyiwnl.iridbot.object;

import me.xflyiwnl.iridbot.Main;

public class User {

    private int id;
    private double irids;

    public User() {
    }

    public User(int id, double irids) {
        this.id = id;
        this.irids = irids;
    }

    public void create(boolean save) {
        Main.getInstance().getUsers().add(this);
        if (save) {
            save();
        }
    }

    public void save() {
        Main.getInstance().getDatabase().getUserDAO().insert(this);
    }

    public void remove() {
        Main.getInstance().getDatabase().getUserDAO().remove(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getIrids() {
        return irids;
    }

    public void setIrids(double irids) {
        this.irids = irids;
    }
}
