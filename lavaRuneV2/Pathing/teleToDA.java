package com.rong.bots.lavaRuneV2.Pathing;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class teleToDA extends Task {

    private Player me;
    private SpriteItem RoD;

    private Coordinate atBank = new Coordinate(2443,3083,0);
    private Area atBankArea = new Area.Circular(atBank,2);

    private Coordinate DuelArena = new Coordinate(3315,3234,0);
    private Area DuelArenaRadius = new Area.Circular(DuelArena,5);

    private String[] anyRing = new String[] {"Ring of dueling(8)","Ring of dueling(7)","Ring of dueling(6)","Ring of dueling(5)","Ring of dueling(4)","Ring of dueling(3)","Ring of dueling(2)"};

    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        return atBankArea.contains(me) && Equipment.contains("Binding necklace") && Equipment.containsAnyOf(anyRing) && Inventory.contains("Pure essence") && Inventory.contains("Earth talisman");
    }

    @Override
    public void execute()
    {
        if(!Bank.isOpen())
        {
            RoD = Equipment.newQuery().names("Ring of dueling(8)","Ring of dueling(7)","Ring of dueling(6)","Ring of dueling(5)","Ring of dueling(4)","Ring of dueling(3)","Ring of dueling(2)").actions("Duel Arena").results().first();;
            if(RoD !=null) {
                if(RoD.interact("Duel Arena")) {
                      Execution.delayUntil(()->DuelArenaRadius.contains(me),2000,3000);
                    //Execution.delay(2000,3000);
                    getLogger().debug("Teleporting to DA");
                }
            }
        }
        else{
            Bank.close();
            Execution.delayUntil(()->!Bank.isOpen(),1000,2000);
        }
    }
}
