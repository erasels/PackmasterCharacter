package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack.DrawGemMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DrawGem extends AbstractGemsCard {
    public final static String ID = makeID("DrawGem");

    public DrawGem() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new DrawGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(1));
    }

    public void upp() {
    }
}