package thePackmaster.cards.bellordpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class TimeOut extends AbstractPackmasterCard {
    public final static String ID = makeID("TimeOut");
    // intellij stuff skill, self, uncommon, , , 7, 2, 1, 1

    public TimeOut() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DrawCardAction(1, new AbstractGameAction() {
            @Override
            public void update() {
                if (DrawCardAction.drawnCards.stream().anyMatch(q -> q.type == CardType.CURSE || q.type == CardType.STATUS || q.color == CardColor.CURSE)) {
                    att(new AllEnemyApplyPowerAction(p, -magicNumber, (q) -> new StrengthPower(q, -magicNumber)));
                }
            }
        }));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}