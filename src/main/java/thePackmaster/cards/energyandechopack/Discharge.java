package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.EnergyAndEchoPack;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Discharge extends AbstractPackmasterCard {

    public final static String ID = makeID(Discharge.class.getSimpleName());

    private static final int COST = 1;

    public Discharge(){
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        baseDamage = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
    }

    @Override
    public void applyPowers() {
        baseDamage = 5;
        baseDamage *= EnergyAndEchoPack.usedEnergy;
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = 5;
        baseDamage *= EnergyAndEchoPack.usedEnergy;
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }
}
