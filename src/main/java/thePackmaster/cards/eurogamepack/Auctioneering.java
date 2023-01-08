package thePackmaster.cards.eurogamepack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.eurogamepack.VictoryPoints;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Auctioneering extends AbstractVPCard{
    public static final String ID = makeID("Auctioneering");

    public Auctioneering() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 4;
        this.baseSecondMagic = this.secondMagic = 4;

        this.baseBlock = block = Math.round(secondMagic/2);

    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (!p.hasPower(VictoryPoints.POWER_ID)){this.applyToSelf(new VictoryPoints(AbstractDungeon.player, 0));}
        this.applyToSelf(new VictoryPoints(AbstractDungeon.player, this.secondMagic));
        this.block = Math.round(AbstractDungeon.player.getPower(VictoryPoints.POWER_ID).amount/magicNumber);
        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
        this.addToBot(new DrawCardAction(p, 1));
    }
    public void upp() {
            this.upgradeSecondMagic(4);
            this.upgradeMagicNumber(-1);
    }
}
