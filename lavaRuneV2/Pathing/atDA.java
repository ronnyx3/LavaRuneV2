package com.rong.bots.lavaRuneV2.Pathing;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class atDA extends Task {

    private Player me;

    private Coordinate DuelArena = new Coordinate(3315,3234,0);
    private Area DuelArenaRadius = new Area.Circular(DuelArena,10);

    private Coordinate FireAltar = new Coordinate(3312,3253,0);
    private Area FireAltarRadius = new Area.Circular(FireAltar,8);

    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        //return(DuelArenaRadius.contains(me) && !FireAltarRadius.contains(me) && Inventory.contains("Pure essence"));
        return DuelArenaRadius.contains(me) && !FireAltarRadius.contains(me) && Inventory.contains("Pure essence");
    }

    @Override
    public void execute()
    {
        RegionPath path = RegionPath.buildTo(FireAltarRadius);
        if(path != null)
        {
            path.step();
            Execution.delayWhile(()->FireAltarRadius.contains(me),1000,2000);
        }
        else
        {
            getLogger().debug("Unable to path to altar");
        }
    }
}
