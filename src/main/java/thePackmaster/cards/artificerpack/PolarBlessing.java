package thePackmaster.cards.artificerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class PolarBlessing extends AbstractArtificerCard {

    public static final String ID = SpireAnniversary5Mod.makeID("PolarBlessing");

    public PolarBlessing(){
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 14;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        blck();
        for (AbstractCard c : getNeighbors()) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!c.isEthereal) {
                        c.retain = true;
                        c.flash();
                    }
                    isDone = true;
                }
            });
        }
    }
}
