package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import thePackmaster.powers.boardgamepack.DicePower;
import thePackmaster.powers.serpentinepack.TaintedEnemy;
import thePackmaster.powers.serpentinepack.TaintedPower;
import thePackmaster.stances.serpentinepack.VenemousStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SinisterConcoction extends AbstractSerpentineCard {

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    private static final int MAGIC = 1;
    public final static String ID = makeID("SinisterConcoction");

    public SinisterConcoction() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ChangeStanceAction(new VenemousStance()));
        //addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new TaintedPower(abstractPlayer, 1)));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new TaintedEnemy(abstractMonster, magicNumber)));
        addToBot(new VFXAction(new PotionBounceEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, abstractMonster.hb.cX, abstractMonster.hb.cY), 0.4f));
    }


    @Override
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}
