package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FriendshipCroquettesSpecialColourless extends AbstractPinnacleCard {

    public final static String ID = makeID("FriendshipCroquettesSpecialColourless");
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 3;


    public FriendshipCroquettesSpecialColourless() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, AbstractCard.CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = MAGIC;
        this.selfRetain = true;
        this.baseBlock = BLOCK;
        this.exhaust = true;
    }

    public void onRetained() {
        upgradeBlock(this.magicNumber);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeBlock(UPGRADE_BLOCK);
    }
}
