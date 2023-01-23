package thePackmaster.cards.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack2.RendGemMod;
import thePackmaster.cards.gemspack.AbstractGemsCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RendGem extends AbstractGemsCard {
    public final static String ID = makeID("RendGem");

    public RendGem() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new RendGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}