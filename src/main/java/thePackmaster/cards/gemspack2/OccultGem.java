package thePackmaster.cards.gemspack2;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack2.OccultGemMod;
import thePackmaster.cards.gemspack.AbstractGemsCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OccultGem extends AbstractGemsCard {
    public final static String ID = makeID("OccultGem");

    public OccultGem() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new OccultGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}