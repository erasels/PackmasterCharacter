package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.weaponspack.*;

import java.util.ArrayList;

public class WeaponsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("WeaponsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public WeaponsPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(4, 2, 3, 3, 2));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(CatONineTails.ID);
        cards.add(DeadlyArsenal.ID);
        cards.add(MegaUpgrade.ID);
        cards.add(PowderKeg.ID);
        cards.add(RearmingStrike.ID);
        cards.add(Salvager.ID);
        cards.add(Sword.ID);
        cards.add(SwordOfChaos.ID);
        cards.add(SwordOfFire.ID);
        cards.add(SwordOfWisdom.ID);
        cards.add(ArmorUp.ID);
        cards.add(PirateHook.ID);
        cards.add(ReapersScythe.ID);
        return cards;
    }
}

