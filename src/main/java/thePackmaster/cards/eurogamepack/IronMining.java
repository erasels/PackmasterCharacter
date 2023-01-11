package thePackmaster.cards.eurogamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.eurogamepack.VictoryPoints;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class IronMining extends AbstractVPCard{
    public static final String ID = makeID("IronMining");

    public IronMining() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseSecondMagic = this.secondMagic = 15;
        this.baseBlock = 16;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                if(Wiz.isAttacking(m)) {
                    Wiz.applyToSelf(new VictoryPoints(p, secondMagic));
                }
                isDone = true;
            }
        });
        this.blck();
    }
    public void upp() {
            this.upgradeBlock(6);
            this.upgradeSecondMagic(5);
    }
}
