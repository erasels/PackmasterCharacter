package thePackmaster.cards.shamanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class IceWall extends AbstractShamanCard {
    public static final String ID = SpireAnniversary5Mod.makeID("IceWall");
    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int UPGRADE_BLOCK = 3;

    public IceWall() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block)));
        this.addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 1, false, true));
    }
}
