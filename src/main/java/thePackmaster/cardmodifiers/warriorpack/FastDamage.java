package thePackmaster.cardmodifiers.warriorpack;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.SpireAnniversary5Mod;

//Currently serves as a block bypass for Rapier
public class FastDamage extends AbstractDamageModifier {
    public static final String ID = SpireAnniversary5Mod.makeID("FastDamage");
    public final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public FastDamage() {}

    @Override
    public boolean ignoresBlock(AbstractCreature target) {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new FastDamage();
    }

    public boolean isInherent() {
        return true;
    }

}