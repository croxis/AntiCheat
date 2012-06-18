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

package net.h31ix.anticheat.util;

import org.bukkit.command.CommandSender;

public enum Permission
{
    /* Check Nodes */
    CHECK,
    CHECK_PHYSICAL,
    CHECK_STATIONARY,
    CHECK_SPEED,
    CHECK_EYE,
    CHECK_STATIONARY_ZOMBE_FLY,
    CHECK_STATIONARY_ZOMBE_NOCLIP,
    CHECK_STATIONARY_ZOMBE_CHEAT,
    CHECK_PHYSICAL_FLY,
    CHECK_PHYSICAL_EXAMPLE,
    CHECK_PHYSICAL_WATERWALK,
    CHECK_EYE_NOSWING,
    CHECK_SPEED_FASTBREAK,
    CHECK_SPEED_FASTPLACE,
    CHECK_STATIONARY_SPAM,
    CHECK_PHYSICAL_SPRINT,
    CHECK_PHYSICAL_SNEAK,
    CHECK_SPEED_SPEEDHACK,
    CHECK_PHYSICAL_SPIDER,
    CHECK_PHYSICAL_NOFALL,
    CHECK_SPEED_FASTBOW,
    CHECK_SPEED_FASTEAT,
    CHECK_SPEED_FASTHEAL,
    CHECK_PHYSICAL_FORCEFIELD,
    CHECK_EYE_XRAY,
    CHECK_EYE_LONGREACH,
    CHECK_SPEED_FASTPROJECTILE,
    CHECK_STATIONARY_ITEMSPAM,
    CHECK_EYE_VISUAL,

    /* System Nodes */
    SYSTEM_LOG,
    SYSTEM_XRAY,
    SYSTEM_RESET,
    SYSTEM_SPY,
    SYSTEM_HELP,
    SYSTEM_UPDATE,
    SYSTEM_REPORT,
    SYSTEM_RELOAD;

    public boolean get(CommandSender cs)
    {
        return cs.hasPermission(toString());
    }

    @Override
    public String toString()
    {
        return "anticheat." + this.name().toLowerCase().replace("_", ".");
    }

}