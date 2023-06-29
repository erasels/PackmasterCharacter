package thePackmaster.cardmodifiers;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.OnInfestCard;
import thePackmaster.powers.OnInfestPower;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class InfestModifier extends AbstractCardModifier {

    public static String MOD_ID = SpireAnniversary5Mod.makeID("Infest");

    private int infestCounter = 0;
    private static final Texture tex = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/ui/infestIcon.png");

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void atEndOfTurn(AbstractCard card, CardGroup group) {
        if (group.equals(AbstractDungeon.player.hand)) {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    card.superFlash(Color.GREEN.cpy());
                    AbstractDungeon.player.hand.moveToDeck(card, true);
                    addInfestCounter(card);
                }
            });
        }
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).text(String.valueOf(infestCounter)).render(card);
    }

    @Override
    public String identifier(AbstractCard card) {
        return MOD_ID;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, InfestModifier.MOD_ID);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new InfestModifier();
    }

    public static int getInfestCount(AbstractCard card) {
        if (hasInfest(card)) {
            ArrayList<AbstractCardModifier> mods = CardModifierManager.getModifiers(card, MOD_ID);
            AbstractCardModifier mod = mods.get(0);
            if (mod instanceof InfestModifier) {
                return ((InfestModifier) mod).infestCounter;
            }
        }
        return 0;
    }

    private void addInfestCounter(AbstractCard card, boolean triggerPowers) {
        infestCounter += 1;
        if (card instanceof OnInfestCard) {
            ((OnInfestCard) card).onInfest(infestCounter);
        }
        if (triggerPowers)
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnInfestPower) {
                    ((OnInfestPower) p).receiveInfest(card);
                }
            }
    }

    private void addInfestCounter(AbstractCard card) {
        addInfestCounter(card, true);
    }

    private void removeInfestCounter(AbstractCard card) {
        infestCounter -= 1;
    }


    public static void incrementInfestCount(AbstractCard card, boolean triggerPowers) {
        if (hasInfest(card)) {
            ArrayList<AbstractCardModifier> mods = CardModifierManager.getModifiers(card, MOD_ID);
            AbstractCardModifier mod = mods.get(0);
            if (mod instanceof InfestModifier) {
                ((InfestModifier) mod).addInfestCounter(card, triggerPowers);
            }
        }
    }

    public static void decrementInfestCount(AbstractCard card) {
        if (hasInfest(card)) {
            ArrayList<AbstractCardModifier> mods = CardModifierManager.getModifiers(card, MOD_ID);
            AbstractCardModifier mod = mods.get(0);
            if (mod instanceof InfestModifier) {
                ((InfestModifier) mod).removeInfestCounter(card);
            }
        }
    }

    public static boolean hasInfest(AbstractCard card) {
        return CardModifierManager.hasModifier(card, MOD_ID);
    }
}
