package thePackmaster.orbs;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.*;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.orbs.WitchesStrike.FullMoon;
import thePackmaster.orbs.contentcreatorpack.Wanderbot;
import thePackmaster.orbs.downfallpack.Ghostflame;
import thePackmaster.orbs.entropy.Oblivion;
import thePackmaster.orbs.spherespack.Blaze;
import thePackmaster.orbs.spherespack.Polar;
import thePackmaster.orbs.summonspack.*;
import thePackmaster.packs.*;

import java.util.ArrayList;

public interface PackmasterOrb {
    ArrayList<AbstractOrb> orbs = new ArrayList<>();

    //If your orb has a non-start of turn/end of turn passive effect, implement this interface/method.
    void passiveEffect();

    static AbstractOrb getPackLimitedOrb(boolean useCardRng) {
        orbs.clear();
        orbs.add(new Dark());
        orbs.add(new Frost());
        orbs.add(new Lightning());
        orbs.add(new Plasma());
        for (AbstractCardPack pack : SpireAnniversary5Mod.currentPoolPacks) {
            if (pack instanceof DownfallPack) {
                orbs.add(new Ghostflame());
            }
            if (pack instanceof WitchesStrikePack) {
                orbs.add(new CrescentMoon());
                orbs.add(new FullMoon());
            }
            if (pack instanceof SummonsPack) {
                orbs.add(new FireSpirit());
                orbs.add(new Leprechaun());
                orbs.add(new Louse());
                orbs.add(new Panda());
                orbs.add(new Porcupine());
                orbs.add(new SwarmOfBees());
                orbs.add(new Wolf());
            }
            if (pack instanceof EntropyPack) {
                orbs.add(new Oblivion());
            }
            if (pack instanceof ContentCreatorPack) {
                orbs.add(new Wanderbot());
            }
            if (pack instanceof SpheresPack) {
                orbs.add(new Blaze());
                orbs.add(new Polar());
            }
        }

        return useCardRng ? orbs.get(AbstractDungeon.cardRandomRng.random(orbs.size() - 1)) : orbs.get(MathUtils.random(orbs.size() - 1));
    }
}
