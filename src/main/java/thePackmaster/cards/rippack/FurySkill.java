package thePackmaster.cards.rippack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FurySkill extends AbstractRippableCard {
    public final static String ID = makeID("FurySkill");

    public FurySkill() {
        this(null, null);
    }

    public FurySkill(AbstractRippedArtCard artCard, AbstractRippedTextCard textCard) {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        baseBlock = 10;
        if (artCard == null && textCard == null) {
            setRippedCards(new FurySkillArt(this), new FurySkillText(this));
        } else if(artCard == null){
            setRippedCards(new FurySkillArt(this), textCard);
        } else {
            setRippedCards(artCard, new FurySkillText(this));
        }
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }
}
