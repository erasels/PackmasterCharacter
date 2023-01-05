package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;


import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AcidicSpray extends AbstractLegacyCard {
    public final static String ID = makeID("AcidicSpray");

    private static final int POWER = 4;
    private static final int UPGRADE_BONUS = 2;
    private static final int MAGIC = 1;
    private static final int MAGIC_UPGRADE = 1;

    public AcidicSpray() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = POWER;
        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractPower pow : m.powers) {
            if (pow.type == AbstractPower.PowerType.DEBUFF)
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
    }

    public void upp() {
            upgradeDamage(UPGRADE_BONUS);
            upgradeMagicNumber(MAGIC_UPGRADE);
    }
}