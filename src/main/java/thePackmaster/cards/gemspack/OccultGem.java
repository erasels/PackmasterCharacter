package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.green.RiddleWithHoles;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack.OccultGemMod;
import thePackmaster.patches.psychicpack.occult.OccultFields;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OccultGem extends AbstractGemsCard {
    public final static String ID = makeID("OccultGem");

    public OccultGem() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        tags.add(CardTags.HEALING);
        OccultFields.isOccult.set(this, true);
        initializeDescription();
    }

    @Override
    public AbstractCardModifier myMod() {
        return new OccultGemMod();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

 
}