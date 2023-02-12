package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.serpentinepack.EvasiveTacticsPower;
import thePackmaster.stances.serpentinepack.CunningStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EvasiveTactics extends AbstractSerpentineCard {

    private static final int COST = 2;
    public final static String ID = makeID("EvasiveTactics");
    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 2;

    public EvasiveTactics() {
        super(ID, COST, AbstractCard.CardType.POWER, CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new EvasiveTacticsPower(abstractPlayer, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}
