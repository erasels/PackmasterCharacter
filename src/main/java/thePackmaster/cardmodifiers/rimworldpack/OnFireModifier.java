package thePackmaster.cardmodifiers.rimworldpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

public class OnFireModifier extends AbstractCardModifier {

    public static String ID = SpireAnniversary5Mod.makeID(OnFireModifier.class.getSimpleName());

    private int fireCounter = 1;
    private static final Texture tex = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/ui/OnFireIcon.png");

    public static String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0] + rawDescription;
    }
    @Override
    public void onInitialApplication(AbstractCard card) {
        card.exhaust = true;
    }

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
                    card.superFlash(Color.ORANGE.cpy());
                    //addToBot(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, fireCounter, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                    fireCounter++;
                }
            });
        }
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if(!card.purgeOnUse) {
        for (int i = 0; i < fireCounter; i++) {
                AbstractMonster m = null;
                if (action.target != null)
                    m = (AbstractMonster)action.target;
                AbstractCard tmp = card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = card.current_x;
                tmp.current_y = card.current_y;
                tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                tmp.target_y = Settings.HEIGHT / 2.0F;
                if (m != null)
                    tmp.calculateCardDamage(m);
                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            }
        }
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).text(String.valueOf(fireCounter)).render(card);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card, ID)) {
            OnFireModifier fireModifier = (OnFireModifier) CardModifierManager.getModifiers(card, ID).get(0);
            fireModifier.fireCounter += fireCounter;
            return false;
        }
        return true;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new OnFireModifier();
    }

}
