package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.serpentinepack.VenemousExitPower;
import thePackmaster.stances.serpentinepack.VenemousStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PoisonedBlades extends AbstractSerpentineCard {

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    public final static String ID = makeID("ApplyPoison");


    public PoisonedBlades() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ChangeStanceAction(new VenemousStance()));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new VenemousExitPower(abstractPlayer, 0), 0));
    }

    @Override
    public void upp() {
        updateCost(UPGRADED_COST);
    }
}
