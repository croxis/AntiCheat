package net.h31ix.anticheat.xray;

import org.bukkit.Location;

public class XrayLocation
{
    private int RECENT_TIME = 30000;
    private Location loc = null;
    private Long time = null;
    
    public XrayLocation(Location l, Long t)
    {
        loc = l;
        time = t;
    }
    
    public Location getLocation()
    {
        return loc;
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
