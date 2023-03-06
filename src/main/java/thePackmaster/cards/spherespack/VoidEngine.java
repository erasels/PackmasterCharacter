package thePackmaster.cards.spherespack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.SpireAnniversary5Mod;

public class VoidEngine extends AbstractSpheresCard {
    public static final String ID = SpireAnniversary5Mod.makeID("VoidEngine");
    private static final int COST = 1;
    private static final int FOCUS = 1;

    public VoidEngine() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = FOCUS;
        this.cardsToPreview = new VoidCard();
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new FocusPower(p, this.magicNumber)));
        this.addToBot(new IncreaseMaxOrbAction(1));
        if (!this.upgraded) {
            this.addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true));
        }
        else {
            this.addToBot(new MakeTempCardInDiscardAction(new VoidCard(), 1));
        }
    }
}
