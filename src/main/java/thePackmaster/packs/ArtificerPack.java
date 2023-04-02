package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.artificerpack.*;

import java.util.ArrayList;

public class ArtificerPack extends AbstractCardPack {

    public static final String ID = SpireAnniversary5Mod.makeID("ArtificerPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public ArtificerPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(SpellStone.ID);
        cards.add(PolarBlessing.ID);
        cards.add(MysticCycle.ID);
        cards.add(GravitySlam.ID);
        cards.add(GrantSwiftness.ID);
        cards.add(WhiteSteelCharm.ID);
        cards.add(EclipseCharm.ID);
        cards.add(Chronobooster.ID);
        cards.add(MirrorsCurse.ID);
        cards.add(BattleFocus.ID);
        return cards;
    }
}
