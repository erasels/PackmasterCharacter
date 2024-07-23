package thePackmaster.cards.basicspack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.ChangePlayedCardExhaustAction;
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
                this.addToTop(new ChangePlayedCardExhaustAction(this, true));
            }
        }));

    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }
}
