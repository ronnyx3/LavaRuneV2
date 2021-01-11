package com.rong.bots.lavaRuneV2.Pathing;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class enterFireAltar extends Task {

    private Player me;
    private GameObject EnterAltar;
    private Coordinate FireAltar = new Coordinate(3312,3253,0);
    private Area FireAltarRadius = new Area.Circular(FireAltar,15);

    private Coordinate InsideAltar = new Coordinate(2576,4848,0);
    private Area InsideAltarRadius = new Area.Circular(InsideAltar,2);


    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        //return FireAltarRadius.contains(me) && Equipment.contains("Fire tiara");
        return FireAltarRadius.contains(me);
    }

    @Override
    public void execute()
    {
        me = Players.getLocal();
        EnterAltar = GameObjects.newQuery().names("Mysterious ruins").actions("Enter").results().nearest();

        if(EnterAltar.isVisible())
        {
            EnterAltar.interact("Enter");
            Execution.delayUntil(()->InsideAltarRadius.contains(me),1000,2000);
            //Execution.delayWhile(()->InsideAltarRadius.contains(me),3000,5000);
            getLogger().debug("Entering fire altar");
        }

        else
        {
            Camera.turnTo(EnterAltar);
            getLogger().debug("Unable to turn to altar");
        }
    }
}
