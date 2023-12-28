package thePackmaster.cards.hermitpack;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.HealVerticalLineEffect;
import thePackmaster.actions.gemspack.ReduceDebuffsAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Virtue extends AbstractHermitCard {
    public final static String ID = makeID("Virtue");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public Virtue() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        this.selfRetain = true;
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        int roll = MathUtils.random(0, 2);
        if (roll == 0) {
            CardCrawlGame.sound.play("HEAL_1");
        } else if (roll == 1) {
            CardCrawlGame.sound.play("HEAL_2");
        } else {
            CardCrawlGame.sound.play("HEAL_3");
        }

        float    X_JITTER = 120.0F * Settings.scale;
        float    Y_JITTER = 120.0F * Settings.scale;
        float    OFFSET_Y = -50.0F * Settings.scale;

        for(int i = 0; i < 18; ++i) {
            AbstractDungeon.effectsQueue.add(new HealVerticalLineEffect((p.hb.cX - p.animX) + MathUtils.random(-X_JITTER * 1.5F, X_JITTER * 1.5F),  p.hb.cY + OFFSET_Y + MathUtils.random(-Y_JITTER, Y_JITTER)));
        }

        Wiz.atb(new ReduceDebuffsAction(AbstractDungeon.player, this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}