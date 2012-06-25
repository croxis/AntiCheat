package net.h31ix.anticheat.xray;

import org.bukkit.Location;
import org.bukkit.Material;

public class XRrayLocation
{
    private int RECENT_TIME = 60000;
    private Location loc = null;
    private Long time = null;
    private Material ore = null;
    
    public XRrayLocation(Material o, Location l, Long t)
    {
        loc = l;
        time = t;
        ore = o;
    }
    
    public Location getLocation()
    {
        return loc;
    }
    
    public Material getOre()
    {
        return ore;
    }
    
    public Long getTime()
    {
        return time;
    }
    
    public boolean isRecent()
    {
        Long now = System.currentTimeMillis();
        Long math = (now - time);
        return math < RECENT_TIME;
    }
    
    public boolean isRecent(Long now)
    {
        Long math = (now - time);
        return math < RECENT_TIME;
    }

}
