package thePackmaster.cards.frostpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Prime extends AbstractFrostCard {
    public final static String ID = makeID("Prime");

    public Prime() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new IncreaseMaxOrbAction(this.magicNumber));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;

                for (AbstractCard c: AbstractDungeon.player.hand.group
                ) {
                    if (c.type==CardType.STATUS || c.type==CardType.CURSE){
                        Wiz.atb(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                    } else {
                        Wiz.atb(new SimpleAddModifierAction(new FrozenMod(), c));
                    }

                }
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}