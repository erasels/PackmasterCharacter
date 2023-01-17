package thePackmaster.cards.metapack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.metapack.WaitMoreAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class KillerInstinct extends AbstractMetaCard implements StartupCard{
    public final static String ID = makeID("KillerInstinct");

    public KillerInstinct() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
    }

    public boolean atBattleStartPreDraw()
    {
        Wiz.shuffleIn(this.makeStatEquivalentCopy(),this.magicNumber);

        Wiz.atb(new WaitMoreAction(1.25F));
        return false;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
