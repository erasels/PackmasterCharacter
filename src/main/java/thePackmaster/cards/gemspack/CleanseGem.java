package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack.CleanseGemMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CleanseGem extends AbstractGemsCard {
    public final static String ID = makeID("CleanseGem");

    public CleanseGem() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new CleanseGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}