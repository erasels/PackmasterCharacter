package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.pixiepack.FetchPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Fetch extends AbstractPixieCard {
    public final static String ID = makeID("Fetch");

    private static final int baseMag = 2;

    public Fetch() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = baseMag;
        this.isEthereal = true;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.isEthereal = false;
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new FetchPower(abstractPlayer, magicNumber)));
    }

}
