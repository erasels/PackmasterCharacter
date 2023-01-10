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

    private static final int BLOCK = 24;
    private static final int UPG_BLOCK = 6;
    private static final int MAGIC = 2;
    private int originalBlock = 24;

    public StoneHelm() {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        baseBlock = originalBlock - (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() * MAGIC);
    }

    public void triggerWhenDrawn(){
        applyPowers();
    }

    public void upp() {
            upgradeBlock(UPG_BLOCK);
            originalBlock += UPG_BLOCK;
            System.out.println(originalBlock);
    }
}