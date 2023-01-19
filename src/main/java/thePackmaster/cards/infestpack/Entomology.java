package thePackmaster.cards.infestpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Entomology extends AbstractInfestCard {
    public final static String ID = makeID("Entomology");
    // intellij stuff attack, enemy, uncommon, 6, 4, , , , 

    public Entomology() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = InfestModifier.getInfestCount(this);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        if (amt > 0) {
            AbstractCard myself = this;
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    InfestModifier.decrementInfestCount(myself);
                }
            });
            atb(new DrawCardAction(1));
            atb(new GainEnergyAction(1));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        int amt = InfestModifier.getInfestCount(this);
        glowColor = amt > 0 ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(4);
    }
}