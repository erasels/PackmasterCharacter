package thePackmaster.powers.gowiththeflowpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.Arrays;

public class AqueductsPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("AqueductsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int cardsDrawnThisTurn;

    public AqueductsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        cardsDrawnThisTurn = AbstractDungeon.player.gameHandSize;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onCardDraw(AbstractCard c) {
        ++cardsDrawnThisTurn;
        if (cardsDrawnThisTurn > AbstractDungeon.player.gameHandSize) {
            flashWithoutSound();
            int[] array = new int[AbstractDungeon.getMonsters().monsters.size()];
            Arrays.fill(array, amount);
            AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(owner, array, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void atStartOfTurn() {
        cardsDrawnThisTurn = 0;
    }
}
