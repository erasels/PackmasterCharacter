package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HunterRank extends AbstractMonsterHunterCard {
    public final static String ID = makeID("HunterRank");
    public final static int UPGRADED_COST = 2;

    public HunterRank() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        doCalc(); //To fix amount not being correct for This Isnt Even My...
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
    }

    public void update(){
        super.update();
        doCalc();
    }

    private void doCalc() {
        baseMagicNumber = CardCrawlGame.elites1Slain + CardCrawlGame.elites2Slain + CardCrawlGame.elites3Slain + CardCrawlGame.elitesModdedSlain + AbstractDungeon.bossCount+1;
        magicNumber = baseMagicNumber;
        this.name = originalName + ": "+magicNumber;
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}