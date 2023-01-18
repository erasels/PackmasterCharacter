package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import thePackmaster.powers.pinnaclepack.Capacitor;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TheSuckamidRareSkill extends AbstractPinnacleCard {

    public final static String ID = makeID("TheSuckamidRareSkill");

    private static final int MAGIC = 8;
    private static final int UPGRADE_MAGIC = 4;
    private static final int MAGIC2 = 2;
    private static final int UPGRADE_MAGIC2 = 1;

    public TheSuckamidRareSkill() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC2;
        this.tags.add(AbstractCard.CardTags.HEALING);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new ApplyPowerAction(p, p, new EquilibriumPower(p, secondMagic), secondMagic));
            addToBot(new HealAction(p, p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondMagic(UPGRADE_MAGIC2);
    }

}
