package thePackmaster.cards.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack2.PetraGemMod;
import thePackmaster.cards.gemspack.AbstractGemsCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PetraGem extends AbstractGemsCard {
    public final static String ID = makeID("PetraGem");

    public PetraGem() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new PetraGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}