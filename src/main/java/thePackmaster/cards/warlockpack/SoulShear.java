package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SoulShear extends AbstractPackmasterCard {
    public final static String ID = makeID(SoulShear.class.getSimpleName());

    private static final int COST = 1;

    public SoulShear() {
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        baseDamage = 7;
        cardsToPreview = new Imp();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new MakeTempCardInDrawPileAction(new Imp(), 1, true, true));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
