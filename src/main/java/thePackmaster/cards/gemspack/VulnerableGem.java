package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack.VulnerableGemMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class VulnerableGem extends AbstractGemsCard {
    public final static String ID = makeID("VulnerableGem");

    public VulnerableGem() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new VulnerableGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}