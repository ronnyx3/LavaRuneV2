package com.rong.bots.lavaRuneV2.Pathing;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.task.Task;

public class toInsideAltar extends Task {

    private Player me;
    private Coordinate InsideAltar = new Coordinate(2576,4848,0);
    private Area InsideAltarRadius = new Area.Circular(InsideAltar,10);

    private Coordinate craftingAltar = new Coordinate(2583,4840,0);
    private Area craftingAltarRadius = new Area.Circular(craftingAltar,2);

    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        return InsideAltarRadius.contains(me) && !craftingAltarRadius.contains(me);
    }

    @Override
    public void execute()
    {
        BresenhamPath path = BresenhamPath.buildTo(craftingAltarRadius);
        if(path!=null)
        {
            path.step();
            getLogger().debug("Walking to altar");
        }
        else{
            getLogger().debug("Unable to walk to INSIDE altar");
        }
    }
}
