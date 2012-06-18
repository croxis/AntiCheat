package net.h31ix.anticheat.checks;

import org.bukkit.entity.Player;

import net.h31ix.anticheat.Anticheat;
import net.h31ix.anticheat.manage.CheckType;

public class Checks
{    
    protected Anticheat core = Anticheat.getPlugin();
    protected CheckType type = CheckType.CHECK;
    protected CheckType check = CheckType.CHECK;
    protected Player player = null;
    
    public Checks(Player pl)
    {
        player = pl;
    }
    
    public boolean willCheck()
    {
        return !check.checkPermission(player) || !type.checkPermission(player);
    }
    
    public boolean check()
    {
        return false;
    }

}
