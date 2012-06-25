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

import java.util.HashMap;
import java.util.Map;

import net.h31ix.anticheat.Anticheat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class XRayStats
{
    private int MIN_BLOCKS = 25;
    private boolean scheduled = false;
    private boolean minedarealore = false;
    private boolean cave = false;
    private String player = null;
    private Map<Material, XrayLocation> ores = null;

    public XRayStats(String pl)
    {
        player = pl;
        ores = new HashMap<Material, XrayLocation>();
    }
    
    public String getPlayerName()
    {
        return player;
    }

    public void logOre(Block ore)
    {
        if (ore.getType() != Material.STONE)
            minedarealore = true;
        
        if(ores.size() < 5 && minedarealore)
            cave = true;

        ores.put(ore.getType(), new XrayLocation(ore.getLocation(), System.currentTimeMillis()));

        if (ores.size() >= MIN_BLOCKS && !scheduled && minedarealore)
        {
            Bukkit.getScheduler().scheduleAsyncDelayedTask(Anticheat.getPlugin(), new Runnable()
            {
                @Override
                public void run()
                {
                    
                }
            }, 20L);
            scheduled = true;
        }
    }
}
