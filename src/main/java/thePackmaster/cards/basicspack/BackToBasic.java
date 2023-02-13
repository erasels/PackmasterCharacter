package thePackmaster.cards.basicspack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BackToBasic extends AbstractPackmasterCard {
    public final static String ID = makeID("BackToBasic");

    public BackToBasic() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, "basics");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FetchAction(p.discardPile, 1, (cards)->{
            if(!cards.isEmpty() && cards.get(0).rarity != CardRarity.BASIC) {
                for (AbstractGameAction a : AbstractDungeon.actionManager.actions)
                    if (a instanceof UseCardAction) {
                        if (BackToBasic.this.equals(ReflectionHacks.getPrivate(a, UseCardAction.class, "targetCard")))
                            ((UseCardAction) a).exhaustCard = true;
                    }
            }
        }));

    }

    @Override
    public void upp() {
        this.retain = true;
    }
}
