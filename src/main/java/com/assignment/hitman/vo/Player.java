package com.assignment.hitman.vo;

/**
 * @author ashutoshp
 */
public class Player {

    // TO-DO: think of to store metadata as well like lastUpdated, createdTime??
    private Integer id;
    private String name;
    private Integer money;
    private Integer health;
    private Integer level;
    private Integer weapon_id;

    public Player(String name) {
        this.name = name;
        this.money = 150;
        this.health = 100;
        this.level = 1;
        this.weapon_id = 2;
    }

    public Integer getId() {
        return id;
    }

    public Player setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMoney() {
        return money;
    }

    public Player setMoney(Integer money) {
        this.money = money;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public Player setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public Player setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getWeapon_id() {
        return weapon_id;
    }

    public Player setWeapon_id(Integer weapon_id) {
        this.weapon_id = weapon_id;
        return this;
    }
}
