package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CaptainsCabin extends AbstractLegacyCard {
    public final static String ID = makeID("CaptainsCabin");

    private static final int AMOUNT_OF_CANNONBALLS = 1;
    private static final int BLOCK_AMOUNT = 14;
    private static final int UPGRADE_PLUS_AMOUNT_OF_CANNONBALLS = 1;
    public CaptainsCabin() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = AMOUNT_OF_CANNONBALLS;
        this.baseBlock = this.block = BLOCK_AMOUNT;
        this.cardsToPreview = new Cannonball();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Cannonball(), this.magicNumber));
    }

    public void upp() {
        this.upgradeMagicNumber(UPGRADE_PLUS_AMOUNT_OF_CANNONBALLS);
    }
}