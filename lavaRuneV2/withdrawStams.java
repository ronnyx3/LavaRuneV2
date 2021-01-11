package com.rong.bots.lavaRuneV2;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

import java.util.regex.Pattern;

public class withdrawStams extends Task {

    private Player me;
    //private SpriteItem RoD;
    //private SpriteItem drinking;
    private final static Pattern POTION_PATTERN = Pattern.compile("^Stamina potion *.*");
    private Coordinate atBank = new Coordinate(2443,3083,0);
    private Area atBankArea = new Area.Circular(atBank,2);

    private String[] anyRing = new String[] {"Ring of dueling(8)","Ring of dueling(7)","Ring of dueling(6)","Ring of dueling(5)","Ring of dueling(4)","Ring of dueling(3)","Ring of dueling(2)"};

    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        //RoD = Equipment.newQuery().names("Ring of dueling(8)","Ring of dueling(7)","Ring of dueling(6)","Ring of dueling(5)","Ring of dueling(4)","Ring of dueling(3)","Ring of dueling(2)").results().first();

        return atBankArea.contains(me) && Equipment.contains("Binding necklace") && Equipment.containsAnyOf(anyRing) && Traversal.getRunEnergy() <25 && !Inventory.containsAnyOf(POTION_PATTERN);
    }

    @Override
    public void execute()
    {
        if(!Bank.isOpen())
        {
            Bank.open();
            Execution.delayUntil(()->Bank.isOpen(),1000,2000);
        }
        else
        {
            Bank.withdraw("Stamina potion(4)",1);
            Execution.delayUntil(()->Inventory.contains("Stamina potion"),1000,2000);
            getLogger().debug("Withdrawing stams");
        }
    }

}
