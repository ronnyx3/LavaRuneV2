package com.rong.bots.lavaRuneV2.Pathing;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

import java.util.regex.Pattern;

public class teleOut extends Task {

    private Player me;
    private SpriteItem ring;
    private Coordinate craftingAltar = new Coordinate(2583,4840,0);
    private Area craftingAltarRadius = new Area.Circular(craftingAltar,3);

    private Coordinate CW = new Coordinate(2438,3089,0);
    private Area CWArea = new Area.Circular(CW,4);

    private final static Pattern Ring_Pattern = Pattern.compile("^Ring of dueling *.*");

    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        return craftingAltarRadius.contains(me) && (Inventory.contains("Lava rune")||Inventory.contains("Fire rune")) && Equipment.contains(Ring_Pattern);
    }

    @Override
    public void execute()
    {
        me = Players.getLocal();
        ring = Equipment.newQuery().names(Ring_Pattern).results().first();

        ring.interact("Castle Wars");
        Execution.delayUntil(()->CWArea.contains(me),2000,3000);
        getLogger().debug("Teleporting to CW");
    }
}
