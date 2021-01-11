package com.rong.bots.lavaRuneV2;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.osrs.local.hud.interfaces.ControlPanelTab;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class wearRing extends Task {

    private SpriteItem RoD;
    private String[] anyRing = new String[] {"Ring of dueling(8)","Ring of dueling(7)","Ring of dueling(6)","Ring of dueling(5)","Ring of dueling(4)","Ring of dueling(3)","Ring of dueling(2)"};

    @Override
    public boolean validate()
    {
        return (Inventory.contains("Ring of dueling(8)") && !Equipment.containsAnyOf(anyRing));
    }

    @Override
    public void execute()
    {
        RoD = Inventory.newQuery().names("Ring of dueling(8)").actions("Wear").results().first();
        if(!Bank.isOpen())
        {
            if(RoD !=null)
            {
                if(ControlPanelTab.INVENTORY.open()) {
                    RoD.click();
                    Execution.delayUntil(()->Equipment.containsAnyOf(anyRing),1000,2000);
                    getLogger().debug("Wore ring");
                }
            }
        }
        else{Bank.close();}
    }
}
