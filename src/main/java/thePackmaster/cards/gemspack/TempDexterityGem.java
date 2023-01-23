package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack.TempDexterityGemMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TempDexterityGem extends AbstractGemsCard {
    public final static String ID = makeID("TempDexterityGem");

    public TempDexterityGem() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new TempDexterityGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}