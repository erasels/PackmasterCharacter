package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.showmanpack.NowYouDontPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SeeMe extends AbstractPackmasterCard {
    public final static String ID = makeID("SeeMe");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SeeMe() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, magicNumber), magicNumber));
        AbstractCard dont = new NowYouDont();
        if (upgraded){
            dont.upgrade();
        }
        this.addToBot(new ApplyPowerAction(p, p, new NowYouDontPower(p, 1, dont), 1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}