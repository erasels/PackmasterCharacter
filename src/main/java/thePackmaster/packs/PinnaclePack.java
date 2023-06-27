package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.pinnaclepack.*;
import java.util.ArrayList;

public class PinnaclePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("PinnaclePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public PinnaclePack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 2, 4, 1, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(DroneSwarmRareSkill.ID);
        cards.add(TheSuckamidRareSkill.ID);
        cards.add(ElectromagneticBurstRarePower.ID);
        cards.add(ArrowStormRareAttack.ID);
        cards.add(TopOfTheSkyUncommonAttack.ID);
        cards.add(BucketOfDregsUncommonSkill.ID);
        cards.add(MarvelousFeastUncommonPower.ID);
        cards.add(PocketTournamentUncommonSkill.ID);
        cards.add(StoneSwordCommonAttack.ID);
        cards.add(Rea__dyCommonSkill.ID);
        cards.add(MysteryCroquettesSpecialColourless.ID);
        cards.add(FishyCroquettesSpecialColourless.ID);
        cards.add(FriendshipCroquettesSpecialColourless.ID);
        cards.add(MeatyCroquettesSpecialColourless.ID);
        return cards;
    }

}
