package thePackmaster.cards.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack2.InspirationGemMod;
import thePackmaster.cards.gemspack.AbstractGemsCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class InspirationGem extends AbstractGemsCard {
    public final static String ID = makeID("InspirationGem");

    public InspirationGem() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new InspirationGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}