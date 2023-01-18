package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.anomalypack.MindOverMatterPower;

public class MindOverMatter extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("MindOverMatter");
    private static final int COST = 2;

    public MindOverMatter() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber= 1;
    }

    @Override
    public void upp() {  this.isInnate = true;  }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new MindOverMatterPower(p, magicNumber), magicNumber));
    }
}
