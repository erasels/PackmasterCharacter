package thePackmaster.packs;

import basemod.helpers.CardBorderGlowManager;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.energyandechopack.GlowEchoMod;
import thePackmaster.cardmodifiers.warlockpack.GlowTheSoulariumMod;
import thePackmaster.cards.energyandechopack.*;
import thePackmaster.cards.quietpack.*;

import java.util.ArrayList;

public class EnergyAndEchoPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("EnergyAndEchoPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public EnergyAndEchoPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(3, 2, 4, 4, 2, "Exhaust", "Tokens"));
    }
    public static int generatedEnergy = 0;
    public static int usedEnergy = 0;

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Crystallize.ID);
        cards.add(Discharge.ID);
        cards.add(Flashlight.ID);
        cards.add(MagnetRise.ID);
        cards.add(Overload.ID);
        cards.add(Panacher.ID);
        cards.add(ShadowStrike.ID);
        cards.add(Slowing.ID);
        cards.add(TearApart.ID);
        cards.add(Voices.ID);
        return cards;
    }

    public void initializePack() {//This is for echo-ed cards to glow
        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
            @Override
            public boolean test(AbstractCard card) {
                return CardModifierManager.hasModifier(card, GlowEchoMod.ID);
            }

            @Override
            public Color getColor(AbstractCard card) {
                return Color.SLATE.cpy();
            }

            @Override
            public String glowID() {
                return "anniv5:GlowEcho";
            }
        });

        super.initializePack();
    }

    public static void resetvalues() {
        generatedEnergy = 0;
        usedEnergy = 0;
    }
}
