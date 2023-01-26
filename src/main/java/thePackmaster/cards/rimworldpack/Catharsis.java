package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.CatharsisPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Catharsis extends AbstractRimCard {
    public final static String ID = makeID(Catharsis.class.getSimpleName());

    public Catharsis() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
        cardsToPreview = new Despair();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new Despair(), 1));
        addToBot(new ApplyPowerAction(p, p, new CatharsisPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}