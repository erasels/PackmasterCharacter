package thePackmaster.cards.instadeathpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.powers.instadeathpack.LockPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Lock extends AbstractInstadeathCard {
    public final static String ID = makeID("Lock");

    public Lock() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);

        this.baseMagicNumber = this.magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HandSelectAction(1, (c) -> true, cards -> {
            for (AbstractCard c : cards)
            {
                addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LockPower(AbstractDungeon.player, c, this.magicNumber), this.magicNumber));
            }
        }, RetainCardsAction.TEXT[0]));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
