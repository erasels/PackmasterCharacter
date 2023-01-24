package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.boardgamepack.DelayedDiceRollAction;
import thePackmaster.cardmodifiers.gemspack.DiceGemMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DiceGem extends AbstractGemsCard {
    public final static String ID = makeID("DiceGem");

    public DiceGem() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new DiceGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DelayedDiceRollAction(6, 1));
    }

    public void upp() {
    }
}