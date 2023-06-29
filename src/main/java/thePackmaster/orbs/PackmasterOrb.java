package thePackmaster.orbs;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.*;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.orbs.WitchesStrike.FullMoon;
import thePackmaster.orbs.contentcreatorpack.Wanderbot;
import thePackmaster.orbs.downfallpack.Ghostflame;
import thePackmaster.orbs.summonspack.*;
import thePackmaster.packs.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public interface PackmasterOrb {
    List<Supplier<AbstractOrb>> genericOrbs = makeGenericSuppliers();
    Map<String, List<Supplier<AbstractOrb>>> packOrbMap = makePackSuppliers();

    //If your orb has a non-start of turn/end of turn passive effect, implement this interface/method.
    void passiveEffect();

    static AbstractOrb getPackLimitedOrb(boolean useCardRng) {
        List<Supplier<AbstractOrb>> possibleOrbs = new ArrayList<>(genericOrbs);
        for (AbstractCardPack pack : SpireAnniversary5Mod.currentPoolPacks) {
            List<Supplier<AbstractOrb>> packOrbs = packOrbMap.get(pack.packID);
            if (packOrbs != null) {
                for (Supplier<AbstractOrb> orbSupplier : packOrbs) {
                    if (!possibleOrbs.contains(orbSupplier)) {
                        possibleOrbs.add(orbSupplier);
                    }
                }
            }
        }

        return useCardRng ? possibleOrbs.get(AbstractDungeon.cardRandomRng.random(possibleOrbs.size() - 1)).get()
                : possibleOrbs.get(MathUtils.random(possibleOrbs.size() - 1)).get();
    }
    
    static List<Supplier<AbstractOrb>> makeGenericSuppliers() {
        List<Supplier<AbstractOrb>> suppliers = new ArrayList<>();

        suppliers.add(Dark::new);
        suppliers.add(Frost::new);
        suppliers.add(Lightning::new);
        suppliers.add(Plasma::new);

        return suppliers;
    }

    static Map<String, List<Supplier<AbstractOrb>>> makePackSuppliers() {
        Map<String, List<Supplier<AbstractOrb>>> supplierMap = new HashMap<>();
        List<Supplier<AbstractOrb>> suppliers;

        suppliers = new ArrayList<>();
        suppliers.add(Ghostflame::new);
        supplierMap.put(DownfallPack.ID, suppliers);


        suppliers = new ArrayList<>();
        suppliers.add(CrescentMoon::new);
        suppliers.add(FullMoon::new);
        supplierMap.put(WitchesStrikePack.ID, suppliers);


        suppliers = new ArrayList<>();
        suppliers.add(FireSpirit::new);
        suppliers.add(Leprechaun::new);
        suppliers.add(Louse::new);
        suppliers.add(Panda::new);
        suppliers.add(Porcupine::new);
        suppliers.add(SwarmOfBees::new);
        suppliers.add(Wolf::new);
        supplierMap.put(SummonsPack.ID, suppliers);


        suppliers = new ArrayList<>();
        suppliers.add(Oblivion::new);
        supplierMap.put(OrbPack.ID, suppliers);


        suppliers = new ArrayList<>();
        suppliers.add(Wanderbot::new);
        supplierMap.put(ContentCreatorPack.ID, suppliers);


        return supplierMap;
    }
}
