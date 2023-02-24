package thePackmaster.cards.creativitypack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.creativitypack.PaintBrushAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PaintBrush extends AbstractCreativityCard {

    public final static String ID = makeID(PaintBrush.class.getSimpleName());

    public PaintBrush() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    @Override
    public void upp() {
        exhaust = false;
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new PaintBrushAction(upgraded));
    }
}
