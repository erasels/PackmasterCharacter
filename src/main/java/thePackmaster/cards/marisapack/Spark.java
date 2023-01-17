package thePackmaster.cards.marisapack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ImpactSparkEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.util.Wiz;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Spark extends AbstractMarisaCard implements AmplifyCard{
    public final static String ID = makeID(Spark.class.getSimpleName());
    private static final int DMG = 3, UPG_DMG = 4;
    private static final int MAGIC = 1, UPG_MAGIC = 1;

    public Spark() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        damage = baseDamage = DMG;
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0; i < 20; i++) {
                    AbstractGameEffect spark = new ImpactSparkEffect(m.hb.cX +
                            MathUtils.random(-(m.hb.width/2), (m.hb.width/2)), m.hb.cY +
                            MathUtils.random(-(m.hb.height/2), (m.hb.height/2)));
                    ReflectionHacks.setPrivate(spark, AbstractGameEffect.class, "color", Color.SKY.cpy());
                    AbstractDungeon.topLevelEffectsQueue.add(spark);
                }
                isDone = true;
            }
        });
        Wiz.atb(new SFXAction("ORB_LIGHTNING_CHANNEL"));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ChargeUpPower(magicNumber));
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }
}
