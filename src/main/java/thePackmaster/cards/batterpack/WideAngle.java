package thePackmaster.cards.batterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.powers.thieverypack.ThieveryMasteryPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WideAngle extends AbstractBatterCard {
    public final static String ID = makeID("WideAngle");

    public WideAngle() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = 7;
        magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int maxblock = Math.min(m.currentBlock,this.magicNumber);
        int stealblock = maxblock;

        if (maxblock > 0) {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    m.loseBlock(stealblock);
                    isDone = true;
                }
            });

            AbstractPower tmp = AbstractDungeon.player.getPower(ThieveryMasteryPower.POWER_ID);
            if (tmp != null) {
                tmp.flash();
                maxblock *= 2;
            }

            Wiz.doBlk(maxblock);
        }
        Wiz.applyToEnemy(m, new WeakPower(m,1,false));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}
