package com.assignment.hitman.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test class for {@link Level} and {@link WeaponType}
 *
 * @author ashutoshp
 */
public class UtilTest {

    @Test
    public void testGetLevelByValue() {
        assertTrue("should be LEVEL1", Level.getLevelFromValue(1).equals(Level.LEVEL1));
        assertTrue("should be LEVEL2", Level.getLevelFromValue(2).equals(Level.LEVEL2));
        assertTrue("should be LEVEL3", Level.getLevelFromValue(3).equals(Level.LEVEL3));
    }

    public void testGetWeaponTypeFromValue() {
        assertTrue("should be KNIFE", WeaponType.getTypeFromValue(1).equals(WeaponType.KNIFE));
        assertTrue("should be PISTOL", WeaponType.getTypeFromValue(2).equals(WeaponType.PISTOL));
        assertTrue("should be REVOLVER", WeaponType.getTypeFromValue(3).equals(WeaponType.REVOLVER));
        assertTrue("should be RIFLE", WeaponType.getTypeFromValue(4).equals(WeaponType.RIFLE));
        assertTrue(
                "should be SPORTING_RIFLE",
                WeaponType.getTypeFromValue(5).equals(WeaponType.SPORTING_RIFLE));
        assertTrue("should be SHOTGUN", WeaponType.getTypeFromValue(6).equals(WeaponType.SHOTGUN));
        assertTrue(
                "should be MACHINE_GUN", WeaponType.getTypeFromValue(7).equals(WeaponType.MACHINE_GUN));
    }
}
