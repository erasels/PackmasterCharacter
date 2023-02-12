package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.serpentinepack.VenemousExitPower;
import thePackmaster.stances.serpentinepack.CunningStance;
import thePackmaster.stances.serpentinepack.VenemousStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Guile extends AbstractSerpentineCard {

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;
    public final static String ID = makeID("Guile");

    public Guile(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new DiscardAction(abstractPlayer, abstractPlayer, magicNumber-1, false));
        addToBot(new ChangeStanceAction(new CunningStance()));
    }

    @Override
    public void upp() {
        updateCost(UPGRADED_COST);
    }
}
