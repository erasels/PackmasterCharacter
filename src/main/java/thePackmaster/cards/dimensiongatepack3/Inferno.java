package thePackmaster.cards.dimensiongatepack3;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import thePackmaster.actions.dimensiongatepack.HoardPayoffAction;
import thePackmaster.actions.dimensiongatepack.SelfDamageAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTrain;
import thePackmaster.util.Wiz;
import thePackmaster.util.cardvars.HoardField;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Inferno extends AbstractDimensionalCardTrain {
    public final static String ID = makeID("Inferno");

    public Inferno() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 16;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()){
                    addToBot(new SelfDamageAction(new DamageInfo(p, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                }
                this.isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}