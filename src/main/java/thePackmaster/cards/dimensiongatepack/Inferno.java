package thePackmaster.cards.dimensiongatepack;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import thePackmaster.actions.dimensiongatepack.InfernoAction;
import thePackmaster.util.cardvars.HoardField;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Inferno extends AbstractDimensionalCard {
    public final static String ID = makeID("Inferno");

    public Inferno() {
        super(ID, -1, CardRarity.RARE, AbstractCard.CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 100;
        setFrame("infernoframe.png");
        isMultiDamage = true;
        PersistFields.basePersist.set(this, 2);
        PersistFields.persist.set(this, 2);
        HoardField.baseHoard.set(this, 9);
        HoardField.hoard.set(this, 9);
        selfRetain = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new InfernoAction(this));
    }


    public void upp() {
        HoardField.upgrade(this, -2);
    }
}