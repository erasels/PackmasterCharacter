package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.grandopeningpack.SurgePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Surge extends AbstractGrandOpeningCard {
    public final static String ID = makeID("Surge");

    public Surge() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(p, p, new SurgePower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }
}