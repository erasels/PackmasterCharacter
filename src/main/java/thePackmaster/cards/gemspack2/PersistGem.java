package thePackmaster.cards.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack2.PersistGemMod;
import thePackmaster.cards.gemspack.AbstractGemsCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PersistGem extends AbstractGemsCard {
    public final static String ID = makeID("PersistGem");

    public PersistGem() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new PersistGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}