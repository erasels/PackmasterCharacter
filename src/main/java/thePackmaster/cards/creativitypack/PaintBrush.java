package thePackmaster.cards.creativitypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.creativitypack.PaintBrushAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PaintBrush extends AbstractCreativityCard {

    public final static String ID = makeID(PaintBrush.class.getSimpleName());

    public PaintBrush() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new PaintBrushAction(upgraded));
    }
}
