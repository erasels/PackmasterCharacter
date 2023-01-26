package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.util.Wiz;

public class ApplyFrosbiteModifier extends AbstractMadScienceModifier {

    public ApplyFrosbiteModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MadScienceModifiers")).TEXT[0] + value + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MadScienceModifiers")).TEXT[1];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
     
        Wiz.applyToEnemy((AbstractMonster)target, new FrostbitePower(target, value));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ApplyFrosbiteModifier(value);
    }
}