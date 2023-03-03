package thePackmaster.cards.secretlevelpack;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class EscapeClause extends AbstractPackmasterCard {
    public final static String ID = makeID("EscapeClause");
    // intellij stuff skill, self, common, , , 7, 3, , 

    public EscapeClause() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (Wiz.getEnemies().stream().anyMatch(q -> q.hasPower(WeakPower.POWER_ID) || q.hasPower(VulnerablePower.POWER_ID))) {
            atb(new BetterDiscardPileToHandAction(1));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = Wiz.getEnemies().stream().anyMatch(q -> q.hasPower(WeakPower.POWER_ID) || q.hasPower(VulnerablePower.POWER_ID)) ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(3);
    }
}