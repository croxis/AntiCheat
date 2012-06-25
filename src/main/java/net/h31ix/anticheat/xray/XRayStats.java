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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import net.h31ix.anticheat.Anticheat;
import net.h31ix.anticheat.util.Distance;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class XRayStats
{
    private int MIN_BLOCKS = 15;
    private long MAX_TIME = 900000L; //TODO: Maybe change to 5 minutes incase of false positives?
    private int MIN_CAVEBLOCKS = 5;
    private int XRAY_DETECTION = 20;
    private int XRAY_Y_DETECTION = 100; //TODO: Remove, this might give extreme false positives.
    private int MAX_STONEBLOCKS = 100; // This is incase if cave detection was previously detected.
    private int MIN_STONECOUNT = 10; //TODO: change for traditional xray detection.
    private boolean scheduled = false;
    private boolean minedarealore = false;
    private boolean cave = false;
    private String player = null;
    private int stonecount = 0;
    private long lastoremined = 0L;
    private boolean xrayer = false;
    private List<XRrayLocation> ores = null;

    public XRayStats(String pl)
    {
        player = pl;
        ores = new CopyOnWriteArrayList<XRrayLocation>();
    }

    public String getPlayerName()
    {
        return player;
    }

    public boolean isXrayer()
    {
        return xrayer;
    }
    
    public int getSpecificOre(Material m)
    {
        int g = 0;
        
        for(XRrayLocation o : ores)
        {
            if(o.getOre().equals(m))
                g++;
        }
        
        return g;
    }
    
    public StringBuilder formatReport(StringBuilder bld)
    {
        bld.append("Stone: " + stonecount);
        bld.append("Iron: " + getSpecificOre(Material.IRON_ORE));
        bld.append("Lapis: " + getSpecificOre(Material.LAPIS_ORE));
        bld.append("Gold: " + getSpecificOre(Material.GOLD_ORE));
        bld.append("Diamond: " + getSpecificOre(Material.DIAMOND_ORE));
        bld.append("Redstone: " + getSpecificOre(Material.REDSTONE_ORE));
        return bld;
    }

    public void logOre(Block ore)
    {
        // Reset, the data's too old.
        if ((System.currentTimeMillis() - lastoremined) > MAX_TIME)
            reset();
        
        if (xrayer)
            return;

        if (ore.getType() != Material.STONE)
            minedarealore = true;

        Anticheat.getManager().log((minedarealore ? "I mined an ore!" : "I mined stone."));

        if (!minedarealore)
            stonecount++;

        Anticheat.getManager().log("Stone count: " + stonecount);

        if (ores.size() < MIN_CAVEBLOCKS && minedarealore)
            cave = true;

        Anticheat.getManager().log(cave ? "I'm in a cave! Something went wrong :(" : "I'm not in a cave :)");

        ores.add(new XRrayLocation(ore.getType(), ore.getLocation(), System.currentTimeMillis()));

        if (ores.size() >= MIN_BLOCKS && !scheduled && minedarealore)
        {
            Anticheat.getManager().log("Scheduling time!");
            Bukkit.getScheduler().scheduleAsyncDelayedTask(Anticheat.getPlugin(), new Runnable()
            {
                @Override
                public void run()
                {
                    // Firstly, are we /really/ in a cave?
                    if (stonecount > MAX_STONEBLOCKS)
                    {
                        //no we're not.
                        cave = false;
                    }

                    if (!cave)
                    {
                        // Traditional XRay detection.
                        Anticheat.getManager().log("Traditional XRay Detection");
                        if (stonecount > MIN_STONECOUNT)
                        {
                            // Begin checking.
                            double x = 0;
                            double y = 0;
                            double z = 0;
                            Location lastloc = null;
                            for (XRrayLocation xl : ores)
                            {
                                Material ore = xl.getOre();
                                Location loc = xl.getLocation();

                                if (!xl.isRecent())
                                    continue;

                                if (!ore.equals(Material.STONE))
                                    break; // First ore hit!

                                if (lastloc != null)
                                {
                                    Distance diff = new Distance(lastloc, loc);

                                    x += diff.getXDifference();
                                    y += diff.getYDifference();
                                    z += diff.getZDifference();
                                }

                                lastloc = loc;
                            }

                            Anticheat.getManager().log("x: " + x + " y: " + y + " z: " + z);

                            if (x < XRAY_DETECTION || y > XRAY_Y_DETECTION || z < XRAY_DETECTION)
                            {
                                xrayer = true;
                                Anticheat.getManager().log("Xray Detected!");
                            }

                        }
                        else
                        {
                            // Check more later.
                            scheduled = false;
                        }
                    }
                    else
                    {
                        // Overly complex "seemstobesmartxrayerbutnotreally" xray detection.
                        Anticheat.getManager().log("Bad XRay Detection.");
                    }

                    if (!xrayer)
                        scheduled = false;
                }
            }, 20L);
            scheduled = true;
        }

        lastoremined = System.currentTimeMillis();
    }

    public void reset()
    {
        ores.clear();
        scheduled = false;
        minedarealore = false;
        lastoremined = 0L;
        cave = false;
        xrayer = false;
        stonecount = 0;
    }
}
