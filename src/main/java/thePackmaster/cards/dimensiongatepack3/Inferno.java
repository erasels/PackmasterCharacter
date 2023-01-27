package thePackmaster.cards.dimensiongatepack3;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import thePackmaster.actions.dimensiongatepack.HoardPayoffAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTrain;
import thePackmaster.util.Wiz;
import thePackmaster.util.cardvars.HoardField;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Inferno extends AbstractDimensionalCardTrain {
    public final static String ID = makeID("Inferno");

    public Inferno() {
        super(ID, -1, CardRarity.RARE, com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 100;
        isMultiDamage = true;
        PersistFields.setBaseValue(this, 2);
        HoardField.setBaseValue(this, 8);
        selfRetain = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HoardPayoffAction(this, () -> {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(Wiz.p(), new ScreenOnFireEffect(), 1.0F));
            Wiz.doAllDmg(Inferno.this, AbstractGameAction.AttackEffect.FIRE, false);
        }));
    }


    public void upp() {
        HoardField.upgrade(this, -2);
    }
}