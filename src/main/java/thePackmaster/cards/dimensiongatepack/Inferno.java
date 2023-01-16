package thePackmaster.cards.dimensiongatepack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.InfernoAction;
import thePackmaster.util.cardvars.HoardField;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Inferno extends AbstractDimensionalCard {
    public final static String ID = makeID("Inferno");

    public Inferno() {
        super(ID, -1, CardRarity.RARE, AbstractCard.CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 100;
        setFrame("infernoframe.png");
        isMultiDamage = true;
        PersistFields.setBaseValue(this, 2);
        HoardField.setBaseValue(this, 9);
        selfRetain = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new InfernoAction(this));
    }


    public void upp() {
        HoardField.upgrade(this, -2);
    }
}