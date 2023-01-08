package thePackmaster.cards.hermitpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HoleUp extends AbstractHermitCard {
    public final static String ID = makeID("HoleUp");

    private static final int BLOCK = 12;
    private static final int UPGRADE_PLUS_BLOCK = 4;

    public HoleUp() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        this.addToBot(new ApplyPowerAction(p, p, new WeakPower(p, magicNumber, false), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
}
