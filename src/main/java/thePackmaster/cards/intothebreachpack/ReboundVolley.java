package thePackmaster.cards.intothebreachpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ReboundVolley extends IntoTheBreachCard implements BranchingUpgradesCard {
    public final static String ID = makeID("ReboundVolley");

    public ReboundVolley() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 6;
        secondDamage = baseSecondDamage = damage;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        for (int i = 0; i < magicNumber; i++)
            atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void upp() {
        if (isBranchUpgrade())
            branchUpgrade();
        else
            baseUpgrade();
        upgradeSecondDamage(baseDamage - baseSecondDamage);
    }

    public void baseUpgrade() {
        upgradeDamage(2);
    }

    public void branchUpgrade() {
        upgradeDamage(-2);
        upgradeMagicNumber(1);
        rawDescription = CardCrawlGame.languagePack.getCardStrings(ReboundVolley.ID).EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    // This method is used so the second damage value on the card
    // (the "to a random enemy" one)
    // doesn't change based on the targeted enemy's powers
    // (e.g. Vulnerable)
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.applyPowers();
        int tmp = baseSecondDamage;
        baseSecondDamage = -1;
        super.calculateCardDamage(mo);
        baseSecondDamage = tmp;
    }
}
