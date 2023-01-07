package thePackmaster.cards.quietpack;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ScrapeFollowUpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.quietpack.FrontflipAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Frontflip extends AbstractPackmasterCard {
    public final static String ID = makeID("Frontflip");

    public Frontflip() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 13;
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.att(new WaitAction(3));
                DrawCardAction.drawnCards.stream().filter(c -> c.cost == -2).forEach(c -> Wiz.att(new DiscardSpecificCardAction(c)));

            }
        }));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upMagic(1);
    }
}


