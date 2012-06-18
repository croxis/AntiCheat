/*
 * AntiCheat for Bukkit.
 * Copyright (C) 2012 AntiCheat Team | http://h31ix.net
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package net.h31ix.anticheat.manage;

import java.util.HashMap;
import java.util.Map;
import net.h31ix.anticheat.util.Permission;
import org.bukkit.entity.Player;

/**
 * <p>
 * All the types of checks and their corresponding permission nodes.
 */

public enum CheckType
{
    CHECK(Permission.CHECK),
    PHYSICAL(Permission.CHECK_PHYSICAL),
    STATIONARY(Permission.CHECK_STATIONARY),
    EYE(Permission.CHECK_EYE),
    EXAMPLE(Permission.CHECK_PHYSICAL_EXAMPLE),
    ZOMBE_FLY(Permission.CHECK_STATIONARY_ZOMBE_FLY),
    ZOMBE_NOCLIP(Permission.CHECK_STATIONARY_ZOMBE_NOCLIP),
    ZOMBE_CHEAT(Permission.CHECK_STATIONARY_ZOMBE_CHEAT),
    FLY(Permission.CHECK_PHYSICAL_FLY),
    WATER_WALK(Permission.CHECK_PHYSICAL_WATERWALK),
    NO_SWING(Permission.CHECK_EYE_NOSWING),
    FAST_BREAK(Permission.CHECK_SPEED_FASTBREAK),
    FAST_PLACE(Permission.CHECK_SPEED_FASTPLACE),
    SPAM(Permission.CHECK_STATIONARY_SPAM),
    SPRINT(Permission.CHECK_PHYSICAL_SPRINT),
    SNEAK(Permission.CHECK_PHYSICAL_SNEAK),
    SPEED(Permission.CHECK_SPEED_SPEEDHACK),
    VISUAL(Permission.CHECK_EYE_VISUAL),
    SPIDER(Permission.CHECK_PHYSICAL_SPIDER),
    NOFALL(Permission.CHECK_PHYSICAL_NOFALL),
    FAST_BOW(Permission.CHECK_SPEED_FASTBOW),
    FAST_EAT(Permission.CHECK_SPEED_FASTEAT),
    FAST_HEAL(Permission.CHECK_SPEED_FASTHEAL),
    FORCEFIELD(Permission.CHECK_PHYSICAL_FORCEFIELD),
    XRAY(Permission.CHECK_EYE_XRAY),
    LONG_REACH(Permission.CHECK_EYE_LONGREACH),
    FAST_PROJECTILE(Permission.CHECK_SPEED_FASTPROJECTILE),
    ITEM_SPAM(Permission.CHECK_STATIONARY_ITEMSPAM);

    private final Permission permission;
    private final Map<String, Integer> level = new HashMap<String, Integer>();

    private CheckType(Permission perm)
    {
        this.permission = perm;
    }

    public boolean checkPermission(Player player)
    {
        return permission.get(player);
    }

    public void logUse(Player player)
    {
        String name = player.getName();
        if (level.get(name) == null)
        {
            level.put(name, 1);
        }
        else
        {
            int amount = level.get(name) + 1;
            level.put(name, amount);
        }
    }

    public void clearUse(Player player)
    {
        level.put(player.getName(), 0);
    }

    public int getUses(Player player)
    {
        int use = 0;
        if (level.get(player.getName()) != null)
        {
            use = level.get(player.getName());
        }
        return use;
    }

    public static String getName(CheckType type)
    {
        char[] chars = type.toString().replaceAll("_", " ").toLowerCase().toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
}
