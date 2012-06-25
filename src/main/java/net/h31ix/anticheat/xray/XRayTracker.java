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

package net.h31ix.anticheat.xray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import net.h31ix.anticheat.Anticheat;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XRayTracker
{

    private static Map<String, XRayStats> xraytrack = new HashMap<String, XRayStats>();
    private static List<String> alerted = new CopyOnWriteArrayList<String>();

    public XRayTracker()
    {
    }

    public boolean hasAlerted(Player pl)
    {
        return alerted.contains(pl.getName());
    }
    
    public boolean sufficientData(String pl)
    {
        return xraytrack.containsKey(pl);
    }

    public void alerted(Player pl, boolean bl)
    {
        if (!bl)
        {
            alerted.remove(pl.getName());
        }
        else
        {
            if (!hasAlerted(pl))
            {
                alerted.add(pl.getName());
            }
        }
    }

    public void removePlayer(Player pl)
    {
        xraytrack.remove(pl.getName());
    }

    public boolean hasAbnormal(String player)
    {
        if (xraytrack.get(player) == null)
        {
            return false;
        }
        else
        {
            return xraytrack.get(player).isXrayer();
        }
    }
    
    public void resetPlayer(Player pl)
    {
        String player = pl.getName();
        if (xraytrack.get(player) == null)
        {
            return;
        }
        else
        {
            alerted.remove(player);
            xraytrack.get(player).reset();
        }
    }

    public void logOre(Player pl, Block ore)
    {
        Anticheat.getManager().log("Loggin ores: " + ore.getType().toString() + " ");
        if (xraytrack.get(pl.getName()) == null)
        {
            XRayStats xr = new XRayStats(pl.getName());
            xr.logOre(ore);
            xraytrack.put(pl.getName(), xr);
        }
        else
        {
            XRayStats xr = xraytrack.get(pl.getName());
            xr.logOre(ore);
        }

    }

    public void sendStats(CommandSender cs, String name)
    {
        StringBuilder report = new StringBuilder();
        XRayStats xr = xraytrack.get(name);
        report.append("-----------XRAY REPORT [" + name + "]-----------");
        report.append("Status: " + (xr.isXrayer() ? ChatColor.RED + "Dirty" : ChatColor.GREEN + "Clean"));
        report = xr.formatReport(report);
    }

}
