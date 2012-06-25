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
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XRayTracker
{
    
    private static Map<String, XRayStats> xraytrack = new HashMap<String, XRayStats>();
    
    public XRayTracker()
    {
    }

    public void removePlayer(Player pl)
    {
        xraytrack.remove(pl.getName());
    }
    
    public boolean hasAbnormal(String player)
    {
        return false;
    }
    
    public void logOre(Player pl, Block ore)
    {
        if(xraytrack.get(pl.getName()) == null)
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
    
    
    
}
