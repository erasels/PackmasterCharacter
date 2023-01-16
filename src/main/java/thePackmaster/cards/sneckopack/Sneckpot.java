package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sneckpot extends AbstractSneckoCard {


    public final static String ID = makeID("Sneckpot");

    public Sneckpot() {
        super(ID, -1, AbstractCard.CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 32;
    }

    //As it is, it behaves unlike any other attacks, strength and other damage modifiers only change the range.
    //Couldn't figure out a better way
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this,
                (energy,params) -> {
            int maxDamage = 0;
            for (int i = 0 ; i < energy ; i++) {
                int r = AbstractDungeon.cardRandomRng.random(1,params[0]);
                if (r > maxDamage) maxDamage = r;
            }
            addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
            addToBot(new DamageAction(m, new DamageInfo(p, maxDamage), AbstractGameAction.AttackEffect.NONE));
            return true;
                }, damage));
    }

    public void upp() {
        upgradeDamage(16);
    }
}
