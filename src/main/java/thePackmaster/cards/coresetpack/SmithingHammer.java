package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SmithingHammer extends AbstractPackmasterCard {
    public final static String ID = makeID("SmithingHammer");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public SmithingHammer() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new UpgradeShineEffect(m.hb.cX, m.hb.cY));
        atb(new WaitAction(0.1f));
        atb(new WaitAction(0.1f));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new WaitAction(0.1f));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new WaitAction(0.1f));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new WaitAction(0.1f));
        for (int i = 0; i < magicNumber; i++) {
            this.addToBot(new UpgradeRandomCardAction());
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}