package thePackmaster.cards.quantapack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Dominate extends AbstractQuantaCard {
    public final static String ID = makeID("Dominate");

    public Dominate() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            this.addToBot(new StunMonsterAction(m, p, this.magicNumber));
        } else {
            for(AbstractMonster monster: Wiz.getEnemies()) {
                this.addToBot(new StunMonsterAction(monster, p, this.magicNumber));
            }
        }
    }

    public void upp() {
            this.target = CardTarget.ALL_ENEMY;
    }
}
