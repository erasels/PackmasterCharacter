package thePackmaster.cards.quantapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MagicDart extends AbstractPackmasterCard {
    public final static String ID = makeID("MagicDart");

    private static final int DAMAGE = 0;

    public MagicDart() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void getMyName()
    {
        if (!this.upgraded)
            this.rawDescription = cardStrings.DESCRIPTION;
        else
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = AbstractDungeon.player.hand.size();
        this.calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        this.getMyName();
        this.initializeDescription();
    }

    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.hand.size();
        super.applyPowers();

        this.getMyName();

        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void upp() {
        if (!upgraded) {
            this.selfRetain = true;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            upgradeName();
            initializeDescription();
        }
    }
}
