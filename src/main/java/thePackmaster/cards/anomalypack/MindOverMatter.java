package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.anomalypack.MindOverMatterPower;
import thePackmaster.util.Wiz;

public class MindOverMatter extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("MindOverMatter");
    private static final int COST = 2;

    public MindOverMatter() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber=1;
    }

    @Override
    public void upp() {  this.isInnate = true;  }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new MindOverMatterPower(p, magicNumber), magicNumber));
    }
}
