package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StoneHelm extends AbstractMonsterHunterCard {
    public final static String ID = makeID("StoneHelm");

    private static final int BLOCK = 26;
    private static final int UPG_BLOCK = 4;
    private static final int MAGIC = 2;

    public StoneHelm() {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public void applyPowers() {
        int origBase = baseBlock;
        baseBlock -= AbstractDungeon.actionManager.cardsPlayedThisTurn.size() * MAGIC;
        if (baseBlock < 0)
            baseBlock = 0;

        super.applyPowers();

        baseBlock = origBase;
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}