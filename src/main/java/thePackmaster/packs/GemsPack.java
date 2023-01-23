package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.arcanapack.*;
import thePackmaster.cards.gemspack.*;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;
import thePackmaster.ui.EnhanceBonfireOption;
import thePackmaster.vfx.gemspack.SocketGemEffect;

import java.util.ArrayList;

public class GemsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("GemsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public static EnhanceBonfireOption socketBonfireOption;
    public static SocketGemEffect currSocketGemEffect = null;
    public static boolean gridScreenForGems = false;
    public static boolean gridScreenForSockets = false;


    public GemsPack() {
        super(ID, NAME, DESC, AUTHOR);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(CleanseGem.ID);
        cards.add(DrawGem.ID);
        cards.add(EnergyGem.ID);
        cards.add(RetainGem.ID);
        cards.add(ShivGem.ID);
        cards.add(TempStrengthGem.ID);
        cards.add(TempDexterityGem.ID);
        cards.add(VulnerableGem.ID);
        cards.add(WardGem.ID);
        cards.add(WeakGem.ID);
        return cards;
    }

}