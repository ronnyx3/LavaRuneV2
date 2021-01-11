package com.rong.bots.lavaRuneV2;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.task.Task;

public class craftingRunes extends Task {

    private Player me;
    private GameObject altar;
    private SpriteItem talis;
    private Coordinate craftingAltar = new Coordinate(2583,4840,0);
    private Area craftingAltarRadius = new Area.Circular(craftingAltar,3);

    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        return craftingAltarRadius.contains(me) && Inventory.contains("Earth talisman") && Inventory.contains("Pure essence");
    }

    @Override
    public void execute()
    {
        altar = GameObjects.newQuery().names("Altar").results().nearest();
        talis = Inventory.newQuery().names("Earth talisman").results().first();

        /*if(altar.isVisible())
        {
            //talis.interact("Use");
            talis.click();
            getLogger().debug("Clicked on talis");
            Execution.delay(500,1000);

            altar.click();
            //altar.interact("Use");
            Execution.delayUntil(()->Inventory.contains("Lava rune"),1000,2000);
            getLogger().debug("Clicked on altar");
            //Execution.delay(1000,2000);
        }
        else{
            Camera.turnTo(altar);
        }*/

        if(altar.isVisible())
        {
            //talis.interact("Use");
            if(talis.interact("Use"))
            {
                Execution.delayUntil(()->Inventory.isItemSelected(),1000,2000);
                getLogger().debug("Selected talis");
            }
            if(altar.interact("Use"))
            {
                //altar.interact("Use");
                Execution.delayUntil(()->Inventory.contains("Lava rune"),2000,3000);
                getLogger().debug("Crafted Lava runes");
            }
        }
        else{
            Camera.turnTo(altar);
        }

    }
}
