package thePackmaster.cards.metapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Swipe extends AbstractMetaCard {
    public final static String ID = makeID("Swipe");

    private static final int DAMAGE = 6;
    private static final int DAMAGE_UPGRADE = 2;

    public Swipe() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.magicNumber = Math.max(p.energy.energyMaster - EnergyPanel.totalCount,0);
        this.baseMagicNumber = magicNumber;

        for (int a = 0; a < this.magicNumber; a++)
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        this.initializeDescription();
    }

    public void applyPowers() {
        this.magicNumber = Math.max(Wiz.p().energy.energyMaster - EnergyPanel.totalCount,0);
        this.baseMagicNumber = magicNumber;

        super.applyPowers();

        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void upp() {
            upgradeDamage(DAMAGE_UPGRADE);
    }
}
