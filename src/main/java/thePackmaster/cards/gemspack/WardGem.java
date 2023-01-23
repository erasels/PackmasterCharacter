package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack.WardGemMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WardGem extends AbstractGemsCard {
    public final static String ID = makeID("WardGem");

    public WardGem() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new WardGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}