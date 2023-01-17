package thePackmaster.cardmodifiers.warriorpack;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;

public class FeralDamage extends AbstractDamageModifier {
    public static final String ID = SpireAnniversary5Mod.makeID("FrontDamage");
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private boolean inherent;

    public FeralDamage() {
        this(true);
    }

    public FeralDamage(boolean isInherent) {
        inherent = isInherent;
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        if (target != null)
            for (AbstractPower pow : target.powers) {
                if (pow.type == AbstractPower.PowerType.DEBUFF)
                    return super.atDamageGive(damage, type, target, card) * 1.5F;
            }

        return super.atDamageGive(damage, type, target, card);
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new FeralDamage();
    }

    public boolean isInherent() {
        return inherent;
    }
}