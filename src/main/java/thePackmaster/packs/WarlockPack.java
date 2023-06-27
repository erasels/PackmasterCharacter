package thePackmaster.packs;

import basemod.helpers.CardBorderGlowManager;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.warlockpack.GlowTheSoulariumMod;
import thePackmaster.cards.warlockpack.*;

import java.util.ArrayList;

public class WarlockPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("WarlockPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public WarlockPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new AbstractCardPack.PackSummary(3, 2, 2, 3, 4, "Exhaust"));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(HighPriestessJeklik.ID);
        cards.add(SoulShear.ID);
        cards.add(Soulfire.ID);
        cards.add(Doomguard.ID);
        cards.add(HandOfGulDan.ID);
        cards.add(SeedsOfDestruction.ID);
        cards.add(AranasiBroodmother.ID);
        cards.add(MalchezaarsImp.ID);
        cards.add(TheSoularium.ID);
        cards.add(RunedMithrilRod.ID);
        cards.add(Imp.ID);
        return cards;
    }

    public void initializePack() {//This is for THe Soularium cards to glow
        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
            @Override
            public boolean test(AbstractCard card) {
                return CardModifierManager.hasModifier(card, GlowTheSoulariumMod.ID);
            }

            @Override
            public Color getColor(AbstractCard card) {
                return Color.PURPLE.cpy();
            }

            @Override
            public String glowID() {
                return "anniv5:GlowTheSoularium";
            }
        });

        super.initializePack();
    }
}
