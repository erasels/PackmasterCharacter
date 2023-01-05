package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LightningStrike extends AbstractPackmasterCard {
    public final static String ID = makeID("LightningStrike");

    private static final int BLOCK_VALUE = 7;
    private static final int UPGRADE_PLUS_BLOCK_VALUE = 3;
    public LightningStrike() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);

        baseBlock = BLOCK_VALUE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
    }

    public void upp() {
        this.upgradeBlock(UPGRADE_PLUS_BLOCK_VALUE);
    }
}