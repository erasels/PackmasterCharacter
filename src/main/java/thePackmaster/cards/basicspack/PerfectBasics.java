package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.basicspack.BasicPerfectionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PerfectBasics extends AbstractBasicsCard{
    public final static String ID = makeID("PerfectBasics");

    public PerfectBasics() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.isInnate = true;
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 1;
        this.baseBlock = this.block = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new BasicPerfectionPower(1)));
    }

    public void upp(){
        this.exhaust = false;
    }
}
