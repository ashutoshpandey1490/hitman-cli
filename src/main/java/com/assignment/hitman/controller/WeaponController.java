package com.assignment.hitman.controller;

import com.assignment.hitman.dao.PlayerDaoImpl;
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
 * All weapon related operations are performed in this class. A singleton implementation.
 *
 * @author ashutoshp
 */
public class WeaponController {

    private WeaponController() {}

    private static class WeaponControllerCreator {
        private static final WeaponController INSTANCE = new WeaponController();
    }

    public static WeaponController getInstance() {
        return WeaponControllerCreator.INSTANCE;
    }

    private static final Writer writer = WriterFactory.getWriter();
    private static final Reader reader = ReaderFactory.getReader();

    public void buyWeapon(Player player) throws SQLException {
        List<Weapon> weaponsList = WeaponDaoImpl.getInstance().getAllWeapons();
        writer.writeInfoMsg(MessageConstants.WEAPON_BUY_MSG);
        printWeaponList(weaponsList);
        writer.writeInputMsg(weaponsList.size() + 1 + " - " + MessageConstants.GO_BACK);
        int input = reader.readInt();
        boolean isValidInput =
                weaponsList
                        .stream()
                        .map(weapon -> weapon.getId())
                        .collect(Collectors.toList())
                        .contains(input);
        if (isValidInput) {
            Weapon requiredWeapon = weaponsList.get(input - 1);
            if (player.getMoney() >= requiredWeapon.getPrice()) {
                int moneyLeft = player.getMoney() - requiredWeapon.getPrice();
                player.setMoney(moneyLeft);
                player.setCurrentWeapon(requiredWeapon);
                PlayerDaoImpl.getInstance().updateExistingPlayer(player);
                writer.writeInfoMsg(
                        MessageConstants.WEAPON_BUY_SUCC_MSG,
                        requiredWeapon.getName(),
                        requiredWeapon.getPrice(),
                        moneyLeft);
                PlayerController.getInstance().startJourney(player);
            } else {
                writer.writeErrorMsg(MessageConstants.INSUFFICIENT_BALANCE, player.getMoney());
                buyWeapon(player);
            }
        } else if (input == weaponsList.size() + 1) {
            PlayerController.getInstance().startJourney(player);
        } else {
            writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
            buyWeapon(player);
        }
    }

    private void printWeaponList(List<Weapon> weaponsList) {
        IntStream.range(0, weaponsList.size())
                .forEach(
                        i ->
                                writer.writeInputMsg(
                                        weaponsList.get(i).getId() + " - " + weaponsList.get(i).toString()));
    }

    /**
     * System user must have randomly selected weapon but it must be of same level as the player to
     * make the game fare.
     *
     * @param user
     * @return randomly selected weapon
     * @throws SQLException
     */
    public Weapon getSystemWeapon(Player user) throws SQLException {
        List<Weapon> weaponsList = WeaponDaoImpl.getInstance().getWeaponByLevel(user.getLevel());
        Random random = new Random();
        int max = weaponsList.size() - 1;
        int min = 0;
        int systemWeaponId = random.nextInt((max - min) + 1) + min;
        return weaponsList.get(systemWeaponId);
    }

    public Weapon getWeaponById(Integer weaponId) throws SQLException {
        return WeaponDaoImpl.getInstance().getWeaponById(weaponId);
    }
}
