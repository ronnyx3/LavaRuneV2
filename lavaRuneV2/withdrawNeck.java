package com.rong.bots.lavaRuneV2;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

public class withdrawNeck extends Task {
    private Player me;
    private Coordinate atBank = new Coordinate(2443,3083,0);
    private Area atBankArea = new Area.Circular(atBank,2);

    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        return atBankArea.contains(me) && !Inventory.contains("Binding necklace") && !Equipment.contains("Binding necklace");
    }

    @Override
    public void execute()
    {
        //if(Bank.isOpen() || !Inventory.contains("Binding necklace"))
        //{
            Bank.open();
            Execution.delayUntil(()->Bank.isOpen(),1000,2000);
            getLogger().debug("Opened bank to withdraw neck");

            Bank.withdraw("Binding necklace",1);
            Execution.delayUntil(()->Inventory.contains("Binding necklace"),1000,2000);
            getLogger().debug("Withdrew necklace");
        //}
    }

}
