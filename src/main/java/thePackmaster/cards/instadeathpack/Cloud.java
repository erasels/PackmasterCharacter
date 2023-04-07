package thePackmaster.cards.instadeathpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MultiGroupMoveAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.instadeathpack.CloudPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cloud extends AbstractInstadeathCard {
    public final static String ID = makeID("Cloud");

    public Cloud() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CloudPower(p, 1), 1));
        //todo - stslib, change text if moving to exhaust
        if (upgraded) {
            addToBot(new MultiGroupMoveAction(CardGroup.CardGroupType.EXHAUST_PILE, 1, CardGroup.CardGroupType.DRAW_PILE, CardGroup.CardGroupType.DISCARD_PILE));
        }
        else {
            addToBot(new MultiGroupMoveAction(CardGroup.CardGroupType.EXHAUST_PILE, 1, CardGroup.CardGroupType.DISCARD_PILE));
        }
    }

    @Override
    public void upp() {

    }
}
