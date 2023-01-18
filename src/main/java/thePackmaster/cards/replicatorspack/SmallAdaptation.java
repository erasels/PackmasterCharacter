package thePackmaster.cards.replicatorspack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SmallAdaptation extends AbstractReplicatorCard {


    public final static String ID = makeID("SmallAdaptation");

    public SmallAdaptation() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseBlock = block = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, block));
        atb(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0],
                (c) -> {
                    return c.cost <= 1;
                }, (cards) -> {
            for (int i = 1; i <= magicNumber; i++) {
                atb(new MakeTempCardInHandAction(cards.get(0).makeStatEquivalentCopy()));
            }
        }));
    }

    public void upp() {
        upgradeBlock(2);
    }
}