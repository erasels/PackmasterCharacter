package thePackmaster.cards.quantapack;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Dominate extends AbstractPackmasterCard {
    public final static String ID = makeID("Dominate");

    public Dominate() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            this.addToBot(new ApplyPowerAction(m, p, new StunMonsterPower(m, this.magicNumber), this.magicNumber));
        } else {
            Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

            while(var3.hasNext()) {
                AbstractMonster mo = (AbstractMonster)var3.next();
                this.addToBot(new ApplyPowerAction(mo, p, new StunMonsterPower(mo, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    public void upp() {
            this.target = CardTarget.ALL_ENEMY;
    }
}
