package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.*;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Bloodlust extends AbstractDarkSoulsCard {
    public final static String ID = makeID("Bloodlust");
    // intellij stuff attack, enemy, common, 12, 4, , , 1,

    public Bloodlust() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new VFXAction(p, new OfferingEffect(), 0.2F));
        Wiz.atb(new VFXAction(p, new IntimidateEffect(p.hb.cX, p.hb.cY),0.2F));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);

        Wiz.applyToSelf(new VulnerablePower(p, 1, false));
        Wiz.atb(new AllEnemyApplyPowerAction(p, magicNumber, (q) -> new VulnerablePower(q, magicNumber, false)));
    }

    public void upp() {
        upgradeDamage(4);
    }
}