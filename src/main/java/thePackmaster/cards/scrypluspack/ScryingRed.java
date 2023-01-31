package thePackmaster.cards.scrypluspack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.scrypluspack.ScryCallbackAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ScryingRed extends AbstractScryPlusCard{

    public final static String ID = makeID(ScryingRed.class.getSimpleName());
    public ScryingRed() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        setMN(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryCallbackAction(magicNumber, list ->
        {
            list.forEach(c -> addToBot(new MakeTempCardInHandAction(c.type == CardType.ATTACK ? new Smite() : new Safety())));
        }));
    }
}
