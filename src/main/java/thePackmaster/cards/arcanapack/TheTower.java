package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.arcanapack.TheTowerAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class TheTower extends AbstractAstrologerCard {
    public final static String ID = makeID("TheTower");
    // intellij stuff attack, enemy, uncommon, 18, 5, , , , 

    public TheTower() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 18;

        AnimatedCardsPatch.loadFrames(this, 7, 0.13f);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new TheTowerAction(this, m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public void upp() {
        upgradeDamage(5);
    }
}