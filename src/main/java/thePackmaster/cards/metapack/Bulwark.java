package thePackmaster.cards.metapack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Bulwark extends AbstractPackmasterCard {
    public final static String ID = makeID("Bulwark");

    public Bulwark() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 0;
        isInnate = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.magicNumber = AbstractDungeon.player.drawPile.size();
        this.baseMagicNumber = this.magicNumber;

        this.addToBot(new AddTemporaryHPAction(p, p, magicNumber));
        this.initializeDescription();
    }

    public void applyPowers() {
        this.magicNumber = AbstractDungeon.player.drawPile.size();
        this.baseMagicNumber = this.magicNumber;

        super.applyPowers();

        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}
