package org.easytours.tourplanner.config;

public class Db {
    private String host;
    private String user;
    private String pw;

    public Db(String host, String user, String pw) {
        this.host = host;
        this.user = user;
        this.pw = pw;
    }

    public Db() {}
}
