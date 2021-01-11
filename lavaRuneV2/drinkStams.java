package com.rong.bots.lavaRuneV2;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

import java.util.regex.Pattern;

public class drinkStams extends Task {

    private Player me;
    private SpriteItem drinking;
    private final static Pattern POTION_PATTERN = Pattern.compile("^Stamina potion *.*");
    private Coordinate atBank = new Coordinate(2443,3083,0);
    private Area atBankArea = new Area.Circular(atBank,2);

    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        return atBankArea.contains(me) && Inventory.containsAnyOf(POTION_PATTERN) && Traversal.getRunEnergy()<90;
    }

    @Override
    public void execute()
    {
        if(Bank.isOpen())
        {
            Bank.close();
            Execution.delayUntil(()->!Bank.isOpen(),1000,2000);
        }
        else {
            drinking = Inventory.newQuery().names(POTION_PATTERN).results().first();
            if (drinking != null) {
                drinking.interact("Drink");
                Execution.delayWhile(() -> Traversal.getRunEnergy() > 100,1000,2000);
                getLogger().debug("Drinking stams");
            }
        }
    }
}

