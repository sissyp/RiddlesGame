package com.aueb.riddlesgame;

public class Avatar {
    String username;
    int level;
    int id;

    public Avatar(String username,int level, int id){
        this.username = username;
        this.level = level;
        this.id = id;
    }

    public int getId(){return id;}

    public String getUsername(){return username;}

    public int getLevel(){return level;}
    public int compareTo(Avatar av) {
        int res = 0;
        if (this.level < av.getLevel()) {
            res =- 1;
        }
        if (this.level > av.getLevel()) {
            res = 1;
        }
        return res;
    }
}
