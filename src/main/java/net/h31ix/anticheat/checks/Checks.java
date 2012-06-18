package net.h31ix.anticheat.checks;

import org.bukkit.entity.Player;

import net.h31ix.anticheat.Anticheat;

public class Checks
{
    protected Anticheat core = null;
    
    public Checks(Anticheat c)
    {
        core = c;
    }
    
    public boolean willCheck(Player player)
    {
        return false;
    }

}
