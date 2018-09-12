package com.assignment.hitman.vo;

import com.assignment.hitman.util.WeaponType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ashutoshp
 */
public class Weapon {
    private Integer id;
    private WeaponType type;
    private String name;
    private Integer price;
    private Integer level;
    private Integer hitValue;

    public Integer getId() {
        return id;
    }

    public Weapon setId(Integer id) {
        this.id = id;
        return this;
    }

    public WeaponType getType() {
        return type;
    }

    public Weapon setType(WeaponType type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Weapon setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Weapon setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public Weapon setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getHitValue() {
        return hitValue;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", type=" + type.getName() +
                ", name='" + name + '\'' +
                ", price=$" + price +
                ", level=" + level +
                ", hitValue=" + hitValue +
                '}';
    }

    public Weapon setHitValue(Integer hitValue) {
        this.hitValue = hitValue;
        return this;
    }

    public static String getAllWeaponsName(List<Weapon> weaponsList) {
        return weaponsList.stream().map(weapon -> weapon.getName()).collect(Collectors.joining(","));
    }
}
