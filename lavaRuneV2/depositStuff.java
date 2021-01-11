package com.rong.bots.lavaRuneV2;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.task.Task;

import java.util.regex.Pattern;

public class depositStuff extends Task {

    private Player me;
    private GameObject Chest;
    String[] musthaveitems = new String[]{"Earth rune"};
    private final static Pattern POTION_PATTERN = Pattern.compile("^Stamina potion *.*");

    private Coordinate atBank = new Coordinate(2438,3092,0);
    private Area atBankArea = new Area.Circular(atBank,10);

    @Override
    public boolean validate()
    {
        me = Players.getLocal();
        //return atBankArea.contains(me) && (Inventory.contains("Lava rune")||Inventory.contains("Fire rune")) || (Inventory.containsAnyOf(POTION_PATTERN) && (Inventory.contains("Lava rune") || Inventory.contains("Fire rune")))
          //      || Inventory.contains("Ring of dueling(1)");
        return atBankArea.contains(me) && (Inventory.contains("Lava rune")||Inventory.contains("Fire rune"));
    }

    @Override
    public void execute()
    {
        //Chest = GameObjects.newQuery().names("Bank chest").results().nearest();
        if(Bank.isOpen())
        {
            if (Bank.depositAllExcept(musthaveitems)) {
                Execution.delayUntil(() -> Inventory.containsOnly(musthaveitems), 1000, 2000);
                getLogger().debug("Deposited stuff");
            }
        }
        else {
            //Chest = GameObjects.newQuery().names("Bank chest").results().nearest();
            //Camera.turnTo(Chest);
            //Camera.concurrentlyTurnTo(Chest);
            Bank.open();
            Execution.delayUntil(() -> Bank.isOpen(), 1000, 2000);
        }
    }
}
