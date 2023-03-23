package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StabManual extends AbstractMonsterHunterCard {
    public final static String ID = makeID("StabManual");

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 2;

    public StabManual() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        this.cardsToPreview = new Shiv();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new MakeTempCardInHandAction(new Shiv(), magicNumber));
        upgradeMagicNumber(1);
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}