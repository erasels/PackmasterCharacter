package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.coresetpack.UpgradeShineEffectSingleHit;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SmithingHammer extends AbstractPackmasterCard {
    public final static String ID = makeID("SmithingHammer");

    public SmithingHammer() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            Wiz.vfx(new UpgradeShineEffectSingleHit(m.hb.cX, m.hb.cY));
            dmg(m, AbstractGameAction.AttackEffect.NONE);
            atb(new WaitAction(0.1f));
        }
        atb(new WaitAction(0.1f));

        this.addToBot(new UpgradeRandomCardAction());
        this.addToBot(new UpgradeRandomCardAction());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}