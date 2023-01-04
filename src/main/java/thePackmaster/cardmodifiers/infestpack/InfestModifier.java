package thePackmaster.cardmodifiers.infestpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.infestpack.OnInfestCard;
import thePackmaster.powers.infestpack.OnInfestPower;
import thePackmaster.util.ImageHelper;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class InfestModifier extends AbstractCardModifier {

    public static String MOD_ID = "caster:Infest";

    private int infestCounter = 0;
    private static final TextureAtlas.AtlasRegion infestImage = ImageHelper.asAtlasRegion(TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/ui/infestIcon.png"));

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
        if (Wiz.isInCombat()) {
            Color color = Color.WHITE.cpy();
            color.a = card.transparency;
            sb.setColor(color);
            sb.draw(infestImage, card.current_x + infestImage.offsetX - (float) infestImage.originalWidth / 2.0F, card.current_y + infestImage.offsetY - (float) infestImage.originalHeight / 2.0F, (float) infestImage.originalWidth / 2.0F - infestImage.offsetX, (float) infestImage.originalHeight / 2.0F - infestImage.offsetY, (float) infestImage.packedWidth, (float) infestImage.packedHeight, card.drawScale * Settings.scale, card.drawScale * Settings.scale, card.angle);
            FontHelper.cardEnergyFont_L.getData().setScale(card.drawScale);
            FontHelper.renderRotatedText(sb, FontHelper.cardEnergyFont_L, Integer.toString(infestCounter), card.current_x, card.current_y, -133.0F * card.drawScale * Settings.scale, 115.0F * card.drawScale * Settings.scale, card.angle, false, Color.WHITE.cpy());
        }
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

    private void addInfestCounter(AbstractCard card) {
        infestCounter += 1;
        if (card instanceof OnInfestCard) {
            ((OnInfestCard) card).onInfest(infestCounter);
        }
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof OnInfestPower) {
                ((OnInfestPower) p).receiveInfest(card);
            }
        }
    }

    private void removeInfestCounter(AbstractCard card) {
        infestCounter -= 1;
    }


    public static void incrementInfestCount(AbstractCard card) {
        if (hasInfest(card)) {
            ArrayList<AbstractCardModifier> mods = CardModifierManager.getModifiers(card, MOD_ID);
            AbstractCardModifier mod = mods.get(0);
            if (mod instanceof InfestModifier) {
                ((InfestModifier) mod).addInfestCounter(card);
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
