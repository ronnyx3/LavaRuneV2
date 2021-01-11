package com.rong.bots.lavaRuneV2;

import com.rong.bots.lavaRuneV2.Pathing.*;
import com.runemate.game.api.script.framework.task.TaskBot;

public class lavaRuneV2 extends TaskBot{

    @Override
    public void onStart(String... strings){
        setLoopDelay(50, 100);
        add(new craftingRunes(),new teleOut(),new depositStuff(),new withdrawRing(),new wearRing(),new withdrawNeck()
                ,new wearNeck(),new withdrawStams(),new drinkStams(),new withdrawEarthTalis(),new withdrawPureEss(), new teleToDA(),new atDA(), new enterFireAltar(),new toInsideAltar());

        //add(new craftingRunes(),new teleOut(),new depositStuff(),new withdrawRing(),new wearRing(),new withdrawNeck()
          //      ,new wearNeck(),new withdrawEarthTalis(),new withdrawPureEss(), new teleToDA(), new enterFireAltar(),new atDA(),new toInsideAltar());
    }
}
