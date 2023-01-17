package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Simplify extends AbstractBasicsCard{
    public final static String ID = makeID("Simplify");

    public Simplify() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(magicNumber));
        for (AbstractCard c : p.hand.group) {
            if (c.rarity != CardRarity.BASIC)
                addToTop(new DiscardSpecificCardAction(c));
        }
    }

    public void upp(){
        this.exhaust = true;
    }
}
