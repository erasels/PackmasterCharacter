package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Prediction extends AbstractWardenCard {
    public final static String ID = makeID("Prediction");

    public Prediction() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
        this.baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        boolean ursafe = true;

        for(AbstractMonster monster: Wiz.getEnemies()) {
            if (monster.getIntentBaseDmg() > 0)
            {
                ursafe = false;
                break;
            }
        }

        if (ursafe)
            this.addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
        else
            this.addToBot(new GainBlockAction(p, p, block));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
