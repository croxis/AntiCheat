package net.h31ix.anticheat.checks.physical;

import net.h31ix.anticheat.manage.CheckType;

import org.bukkit.entity.Player;

public class ExampleCheck extends PhysicalChecks
{

    public ExampleCheck(Player pl)
    {
        super(pl);
        check = CheckType.EXAMPLE;
    }
    
    @Override
    public boolean check()
    {
        if(physdata.moved < 1) {
            physdata.moved += 1;
            return true; // user passed the check.
        }
        
        physdata.moved += 1;
        
        if(physdata.moved > 3) {
            physdata.moved = 0;
            return false; // user did not pass the check.
        }
        
        return true;
    }

}
