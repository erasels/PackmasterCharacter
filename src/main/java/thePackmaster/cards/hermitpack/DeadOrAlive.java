package thePackmaster.cards.hermitpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.actions.hermitpack.DeadOrAliveAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DeadOrAlive extends AbstractHermitCard {
    public final static String ID = makeID("DeadOrAlive");

    private static final int DAMAGE = 8;
    private static final int DAMAGE_UP = 3;

    public DeadOrAlive() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        this.exhaust=true;
        this.tags.add(AbstractCard.CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }

        int num = energyOnUse;

        if (p.hasRelic("Chemical X")) {
            num += 2;
            p.getRelic("Chemical X").flash();
        }

        for (int i = 0; i < num; i++)
        this.addToBot(new DeadOrAliveAction(m,p, this));

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public void upp() {
        upgradeDamage(DAMAGE_UP);
    }
}