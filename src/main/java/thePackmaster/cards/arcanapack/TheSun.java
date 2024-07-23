package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;
import thePackmaster.vfx.arcanapack.SunEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.atb;

public class TheSun extends AbstractAstrologerCard {
    public final static String ID = makeID("TheSun");
    // intellij stuff attack, all_enemy, rare, 22, 6, , , 3, 

    public TheSun() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 22;
        baseMagicNumber = magicNumber = 99;
        exhaust = true;

        this.isMultiDamage = true;

        AnimatedCardsPatch.loadFrames(this, 44, 0.12f);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        float time = 0.1f;
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDeadOrEscaped())
                time += SunEffect.DELAY_TIME;
        }
        atb(new VFXAction(new SunEffect(), time));
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                AbstractMonster strongest = null;
                for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                    if (!mo.isDeadOrEscaped()) {
                        if (strongest == null) {
                            strongest = mo;
                        }
                        else if (mo.currentHealth > strongest.currentHealth) {
                            strongest = mo;
                        }
                    }
                }

                if (strongest != null) {
                    applyToEnemy(strongest, new VulnerablePower(strongest, TheSun.this.magicNumber, false));
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(8);
    }
}