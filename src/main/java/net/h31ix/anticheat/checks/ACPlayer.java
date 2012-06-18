package net.h31ix.anticheat.checks;

import org.bukkit.entity.Player;

import net.h31ix.anticheat.checks.physical.PhysicalData;

public class ACPlayer
{
    private PhysicalData physdata;
    private Player bukkitplayer;
    
    public ACPlayer(Player player)
    {
        bukkitplayer = player;
        physdata = new PhysicalData(player);
    }

}
