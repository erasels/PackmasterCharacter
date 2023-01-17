package thePackmaster.cards.anomalypack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import thePackmaster.cards.AbstractPackmasterCard;


import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class RitualSight extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("RitualSight");
    private static final int COST = 1;
    private static final int BLOCK = 11;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public RitualSight() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_PLUS_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
            att(new ExhaustSpecificCardAction(cards.get(0), p.hand, true));
            att(new MakeTempCardInDrawPileAction(cards.get(0),1,true,false));

        }));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }
}
