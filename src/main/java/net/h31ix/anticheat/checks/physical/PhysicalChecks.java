package net.h31ix.anticheat.checks.physical;

import org.bukkit.entity.Player;

import net.h31ix.anticheat.checks.Checks;
import net.h31ix.anticheat.manage.CheckManager;
import net.h31ix.anticheat.manage.CheckType;

public class PhysicalChecks extends Checks
{
    protected PhysicalData physdata = null;
    
    public PhysicalChecks(Player pl)
    {
        super(pl);
        check = CheckType.PHYSICAL;
        type = CheckType.PHYSICAL;
        physdata = CheckManager.playerlist.get(pl.getName()).getPhysicalData();
    }

}
