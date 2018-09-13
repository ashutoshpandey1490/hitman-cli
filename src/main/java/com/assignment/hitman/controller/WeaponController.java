package com.assignment.hitman.controller;

import com.assignment.hitman.dao.PlayerDao;
import com.assignment.hitman.dao.PlayerDaoImpl;
import com.assignment.hitman.dao.WeaponDao;
import com.assignment.hitman.dao.WeaponDaoImpl;
import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.vo.Player;
import com.assignment.hitman.vo.Weapon;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ashutoshp
 */
public class WeaponController {

    private Writer writer = WriterFactory.getWriter();
    private Reader reader = ReaderFactory.getReader();

    public void buyWeapon(Player player) throws SQLException {
        WeaponDao weaponDao = new WeaponDaoImpl();
        List<Weapon> weaponsList = weaponDao.getAllWeapons();
        writer.writeInfoMsg(MessageConstants.WEAPON_BUY_MSG);
        // TODO - can we put it one place?
        IntStream.range(0, weaponsList.size()).forEach(i -> writer.writeInputMsg(weaponsList.get(i).getId()+ " - " +
                weaponsList.get(i).toString()));
        writer.writeInputMsg(weaponsList.size()+1+" - Go back");
        int input = reader.readInt();
        boolean isValidInput = weaponsList.stream().map(weapon -> weapon.getId()).collect(Collectors.toList()).contains(input);
        if(isValidInput){
            Weapon requiredWeapon = weaponsList.get(input - 1);
            if(player.getMoney() >= requiredWeapon.getPrice()) {
                int moneyLeft = player.getMoney() - requiredWeapon.getPrice();
                player.setMoney(moneyLeft);
                player.setCurrentWeapon(requiredWeapon);
                PlayerDao playerDao = new PlayerDaoImpl();
                playerDao.updateExistingPlayer(player);
                writer.writeInfoMsg(MessageConstants.WEAPON_BUY_SUCC_MSG, requiredWeapon.getName(), requiredWeapon.getPrice(), moneyLeft);
                PlayerController playerController = new PlayerController();
                playerController.startJourney(player);
            }
            else {
                writer.writeErrorMsg(MessageConstants.INSUFFICIENT_BALANCE, player.getMoney());
                buyWeapon(player);
            }
        } else if(input == weaponsList.size()+1) {
            PlayerController playerController = new PlayerController();
            playerController.startJourney(player);
        }
        else {
            writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
            buyWeapon(player);
        }
    }

    public Weapon getSystemWeapon(Player user) throws SQLException {
        WeaponDao weaponDao = new WeaponDaoImpl();
        List<Weapon> weaponsList = weaponDao.getWeaponByLevel(user.getLevel());
        Random random = new Random();
        int max = weaponsList.size() -1; int min = 0;
        int systemWeaponId = random.nextInt((max - min) + 1) + min;
        return weaponsList.get(systemWeaponId);
    }

    public Weapon getWeaponById(Integer weaponId) throws SQLException {
        WeaponDao weaponDao = new WeaponDaoImpl();
        return weaponDao.getWeaponById(weaponId);
    }
}
