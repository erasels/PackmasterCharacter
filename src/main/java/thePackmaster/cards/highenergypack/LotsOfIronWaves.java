package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LotsOfIronWaves extends AbstractHighEnergyCard {
    public final static String ID = makeID("LotsOfIronWaves");
    // intellij stuff attack, enemy, uncommon, 22, 5, 22, 5, , 

    public LotsOfIronWaves() {
        super(ID, 4, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 22;
        baseBlock = 22;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new WaitAction(0.1F));
        if (p != null && m != null) {
            this.addToBot(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
        }
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    public void upp() {
        upgradeDamage(5);
        upgradeBlock(5);
    }
}