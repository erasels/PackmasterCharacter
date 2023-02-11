package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.upgradespack.SuperUpgradeAction;

import java.util.*;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class EternalStrike extends AbstractCosmosCard {
    public final static String ID = makeID("EternalStrike");

    public EternalStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = 7;
        magicNumber = baseMagicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    public void triggerOnExhaust() {
        AbstractCard copyOfThis = this.makeCopy();

        ArrayList<AbstractCard> cardList = new ArrayList<>();
        cardList.add(copyOfThis);
        if (timesUpgraded > 1)
            atb(new SuperUpgradeAction(cardList, timesUpgraded));
        else if (upgraded)
            copyOfThis.upgrade();

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                copyOfThis.damage = copyOfThis.baseDamage = baseDamage + magicNumber;
                this.isDone = true;
            }
        });

        atb(new MakeTempCardInHandAction(copyOfThis));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}