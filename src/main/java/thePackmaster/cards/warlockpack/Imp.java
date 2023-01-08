package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;


public class Imp extends AbstractPackmasterCard {
    public final static String ID = makeID(Imp.class.getSimpleName());

    private static final int COST = -2;

    public Imp() {
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY);
        magicNumber = baseMagicNumber = 4;
        secondDamage = baseSecondMagic = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        thornDmg(m, magicNumber);
    }

    @Override
    public void triggerWhenDrawn() {
        use(adp(), AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng));
        //Increase damage action
        atb(new DrawCardAction(1));
    }

    @Override
    public void upp() {
        upMagic(1);//Apotheosis interaction Pog ?
    }
}
