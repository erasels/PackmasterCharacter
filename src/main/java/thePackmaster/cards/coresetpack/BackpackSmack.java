package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class BackpackSmack extends AbstractPackmasterCard {
    public final static String ID = makeID("BackpackSmack");

    private boolean synergyOn;

    public BackpackSmack() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        synergyOn = (hasSynergy());

        atb(new EasyXCostAction(this, (effect, params) -> {
            if (synergyOn && effect > 0) {
                Wiz.applyToEnemyTop(m, new VulnerablePower(m, effect, false));
                Wiz.applyToEnemyTop(m, new WeakPower(m, effect, false));
            }
            for (int i = 0; i < effect; i++) {
                dmgTop(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            }
            return true;
        }));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (hasSynergy()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}