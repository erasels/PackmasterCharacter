package thePackmaster.cards.enchanterpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class MysticCycle extends AbstractEnchanterCard {

    public static final String ID = SpireAnniversary5Mod.makeID("MysticCycle");
    public static final UIStrings strings = CardCrawlGame.languagePack.getUIString(ID);

    public MysticCycle(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(2, strings.TEXT[0], true,true, card -> true,
                (list) -> {
                    for (AbstractCard c : list) {
                        Wiz.hand().moveToDiscardPile(c);
                        addToBot(new DrawCardAction(1));
                    }
                }
        ));
        addToBot(new GainEnergyAction(magicNumber));
    }

}
