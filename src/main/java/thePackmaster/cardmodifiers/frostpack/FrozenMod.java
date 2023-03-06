package thePackmaster.cardmodifiers.frostpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.energyandechopack.EchoedEtherealMod;
import thePackmaster.cardmodifiers.energyandechopack.GlowEchoMod;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import static thePackmaster.util.Wiz.atb;

public class FrozenMod extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID("FrozenMod");

    private int originalCost;
    private boolean hadRetain = false;
    private static final Texture tex = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/ui/frozenOverlay.png");

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return false;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        CardCrawlGame.sound.play("ORB_FROST_CHANNEL", 0.1F);
        originalCost = card.cost;
        card.modifyCostForCombat(1);
        if (card.selfRetain) hadRetain = true;
        card.selfRetain = true;
        card.tags.add(SpireAnniversary5Mod.FROZEN);
    }

    @Override
    public void onRemove(AbstractCard card) {
        card.tags.remove(SpireAnniversary5Mod.FROZEN);

        //Adds another modifier which hands over the original cost of the card when Frozen, so the card can revert back to its original cost when played.
        CardModifierManager.addModifier(card, new RevertCostWhenPlayedMod(originalCost));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                if (!hadRetain) {
                    card.selfRetain = false;
                }
            }
        });
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        float drawX = card.current_x - 256.0F;
        float drawY = card.current_y - 256.0F;
        sb.draw(tex, drawX, drawY, 256.0F, 256.0F, 512.0F, 512.0F, card.drawScale * Settings.scale, card.drawScale * Settings.scale, card.angle, 0, 0, 512, 512, false, false);

    }

    @Override
    public void atEndOfTurn(AbstractCard card, CardGroup group) {


            //Used as an action here in case this is triggered immediately at end of turn from Cold Storage.
            //If Cold Storage picks a 0-cost, it needs to freeze the card, then unfreeze immediately, which it can't unless this is an action.
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    this.isDone = true;
                    card.modifyCostForCombat(-1);
                    if (card.cost <= 0){
                    CardModifierManager.removeSpecificModifier(card, FrozenMod.this, true);

                    }
                }
            });

    }

    public AbstractCardModifier makeCopy() {
        return new FrozenMod();
    }
}
