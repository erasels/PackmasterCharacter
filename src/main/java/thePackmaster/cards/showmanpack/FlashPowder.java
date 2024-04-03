package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FlashPowder extends AbstractPackmasterCard {
    public final static String ID = makeID("FlashPowder");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FlashPowder() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (AbstractMonster mo : Wiz.getEnemies()){
           addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false), 1));
        }
    }

    public void upp() {
        upgradeBlock(3);
    }
}