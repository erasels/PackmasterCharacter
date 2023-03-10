package thePackmaster.cardmodifiers.goddessofexplosionspack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import java.util.Arrays;
import java.util.List;

public class DamageAllEnemiesModifier extends AbstractCardModifier{
    public static String MOD_ID = SpireAnniversary5Mod.makeID("GoddessOfExplosions");
    UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("KillerQueenTooltip"));

    public int value;
    private static final Texture tex = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/ui/killerQueenIcon.png");

    public DamageAllEnemiesModifier(int valueIn){
        value = valueIn;
    }

    @Override
    public String identifier(AbstractCard card) {
        return MOD_ID;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(tex).text(String.valueOf(value)).render(card);
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        TooltipInfo ti = new TooltipInfo(uiStrings.TEXT[0],uiStrings.TEXT[1] + value + uiStrings.TEXT[2]);
        return Arrays.asList(ti);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        CardCrawlGame.sound.playV(SpireAnniversary5Mod.DETONATOR_KEY, 1.0f);
        Wiz.atb(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(value, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    public AbstractCardModifier makeCopy() {
        return new DamageAllEnemiesModifier(value);
    }
}