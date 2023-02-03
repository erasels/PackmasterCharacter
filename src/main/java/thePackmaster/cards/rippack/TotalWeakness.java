package thePackmaster.cards.rippack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cardmodifiers.rippack.RippableModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.forAllMonstersLiving;

public class TotalWeakness extends AbstractRipCard {
    public final static String ID = makeID("TotalWeakness");


    public TotalWeakness() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 2;
        CardModifierManager.addModifier(this, new RippableModifier());
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        forAllMonstersLiving(this::applyWeak);
    }

    private void applyWeak(AbstractMonster m){
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }
}
