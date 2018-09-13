package com.assignment.hitman.vo;

/** @author ashutoshp */
public class Player {

    // TODO: think of to store metadata as well like lastUpdated, createdTime??
    // TODO: Make it builder pattern
    private Integer id;
    private String name;
    private Integer money;
    private Integer health;
    private Integer level;
    private Integer weaponId;
    private Weapon currentWeapon;
    // TODO- can we make Opponent an inner class?
    private Integer opponentHealth = 80;
    private Integer opponentWeaponId;

    @Override
    public String toString() {
        return "Player{"
                + "  name='"
                + name
                + '\''
                + ", money="
                + money
                + ", health="
                + health
                + ", level="
                + level
                + ", weaponName="
                + currentWeapon.getName()
                + '}';
    }

    public Player(String name) {
        this.name = name;
        this.money = 150;
        this.health = 100;
        this.level = 1;
        this.weaponId = 2;
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

    public Integer getWeaponId() {
        return weaponId;
    }

    public Player setWeaponId(Integer weaponId) {
        this.weaponId = weaponId;
        return this;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public Player setCurrentWeapon(Weapon currentWeapon) {
        this.weaponId = currentWeapon.getId();
        this.currentWeapon = currentWeapon;
        return this;
    }

    public Integer getOpponentHealth() {
        return opponentHealth;
    }

    public Player setOpponentHealth(Integer opponentHealth) {
        this.opponentHealth = opponentHealth;
        return this;
    }

    public Integer getOpponentWeaponId() {
        return opponentWeaponId;
    }

    public Player setOpponentWeaponId(Integer opponentWeaponId) {
        this.opponentWeaponId = opponentWeaponId;
        return this;
    }
}
