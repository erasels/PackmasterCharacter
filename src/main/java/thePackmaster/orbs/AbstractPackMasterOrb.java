package thePackmaster.orbs;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.*;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.orbs.WitchesStrike.FullMoon;
import thePackmaster.orbs.downfallpack.Ghostflame;
import thePackmaster.orbs.entropy.Oblivion;
import thePackmaster.orbs.summonspack.Bulldog;
import thePackmaster.orbs.summonspack.Panda;
import thePackmaster.orbs.summonspack.SwarmOfBees;
import thePackmaster.packs.*;

import java.util.ArrayList;

public abstract class AbstractPackMasterOrb extends CustomOrb {
    public static ArrayList<AbstractOrb> orbs = new ArrayList();
    public AbstractPackMasterOrb(String ID, String NAME, int basePassiveAmount, int baseEvokeAmount, String passiveDescription, String evokeDescription, String imgPath) {
        super(ID, NAME, basePassiveAmount, baseEvokeAmount, passiveDescription, evokeDescription, imgPath);
    }
    public void PassiveEffect(){

    }
    public static AbstractOrb getPackLimitedOrb(boolean useCardRng) {
       orbs.clear();
        for (AbstractCardPack pack : SpireAnniversary5Mod.currentPoolPacks){
            if (pack instanceof DefectPack){
                orbs.add(new Dark());
                orbs.add(new Frost());
                orbs.add(new Lightning());
                orbs.add(new Plasma());
            }
            if (pack instanceof DownfallPack){
                orbs.add(new Ghostflame());
            }
            if (pack instanceof WitchesStrikePack){
                orbs.add(new CrescentMoon());
                orbs.add(new FullMoon());
            }
            if (pack instanceof StrikesPack){
                orbs.add(new Dark());
                orbs.add(new Lightning());
            }
            if (pack instanceof SummonsPack){
                orbs.add(new Bulldog(3));
                orbs.add(new Panda(5));
                orbs.add(new SwarmOfBees());
            }
            if (pack instanceof EntropyPack){
                orbs.add(new Oblivion());
            }
        }
        if (orbs.isEmpty()){
            return AbstractOrb.getRandomOrb(useCardRng);
        } else {
            return useCardRng ? orbs.get(AbstractDungeon.cardRandomRng.random(orbs.size() - 1)) : orbs.get(MathUtils.random(orbs.size() - 1));
        }
    }
}
