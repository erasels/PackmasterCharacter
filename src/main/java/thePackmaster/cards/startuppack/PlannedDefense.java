package thePackmaster.cards.startuppack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PlannedDefense extends AbstractStartUpCard {
    public final static String ID = makeID("PlannedDefense");

    public PlannedDefense() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.isInnate = true;
        this.baseBlock = this.block = 12;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
        boolean innatePlayed = false;
        for(int c = 0; c<AbstractDungeon.actionManager.cardsPlayedThisTurn.size()-1; c++) {
            if(AbstractDungeon.actionManager.cardsPlayedThisTurn.get(c).isInnate) {
                innatePlayed = true;
                break;
            }
        }
        if (innatePlayed) {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new NextTurnBlockPower((AbstractCreature) p, this.block), this.block));
        }
    }

    @Override
    public void upp() {
        this.upgradeBlock(3);
    }
}