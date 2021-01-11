package com.rong.bots.lavaRuneV2;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.osrs.local.hud.interfaces.ControlPanelTab;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class wearNeck extends Task {

    private SpriteItem Necklace;

    @Override
    public boolean validate()
    {
        return (Inventory.contains("Binding necklace") && !Equipment.contains("Binding necklace"));
    }

    @Override
    public void execute()
    {
        Necklace = Inventory.newQuery().names("Binding necklace").actions("Wear").results().first();
        if(!Bank.isOpen())
        {
            if(Necklace !=null)
            {
                if(ControlPanelTab.INVENTORY.open()) {
                    Necklace.click();
                    Execution.delayUntil(()->Equipment.contains("Binding necklace"),1000,2000);
                    getLogger().debug("Wore necklace");
                }
            }
        }
        else{Bank.close();}
    }
}
