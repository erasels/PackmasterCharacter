package thePackmaster.cards.quantapack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Potential extends AbstractQuantaCard {
    public final static String ID = makeID("Potential");

    public Potential() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 10;
        this.baseSecondMagic = 1;
        this.magicNumber = this.baseMagicNumber;
        this.secondMagic = this.baseSecondMagic;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(p, p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
    }
}
