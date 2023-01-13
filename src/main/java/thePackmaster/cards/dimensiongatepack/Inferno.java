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
import thePackmaster.util.cardvars.HoardField;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Inferno extends AbstractDimensionalCard {
    public final static String ID = makeID("Inferno");

    public Inferno() {
        super(ID, -1, CardRarity.RARE, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ALL);
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
        BaseMod.logger.info("Inferno's MN start: " + HoardField.hoard.get(this));
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        if (p.hasRelic(ChemicalX.ID)) {
            energyOnUse = energyOnUse + 2;
        }
        BaseMod.logger.info("Inferno's energy on use: " + HoardField.hoard.get(this));
        if (HoardField.hoard.get(this) > 0) {
            HoardField.decrement(this, this.energyOnUse);
            if (HoardField.hoard.get(this) <= 0) {
                AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ScreenOnFireEffect(), 1.0F));
                allDmg(AbstractGameAction.AttackEffect.FIRE);
                HoardField.resetValueToBase(this);
            }
        } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ScreenOnFireEffect(), 1.0F));
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            HoardField.resetValueToBase(this);
        }
        if (!this.freeToPlayOnce) {
            AbstractDungeon.player.energy.use(EnergyPanel.totalCount);
        }
        BaseMod.logger.info("Inferno's MN end: " + HoardField.hoard.get(this));
    }


    public void upp() {
        HoardField.upgrade(this, -2);
    }
}