package net.h31ix.anticheat.checks;

import org.bukkit.entity.Player;

import net.h31ix.anticheat.Anticheat;

public class Data
{
    protected Anticheat core = Anticheat.getPlugin();
    protected Player bplayer;
    
    public Data(Player player)
    {
        bplayer = player;
    }

}
