package thePackmaster.cards.sneckopack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WhipNSnapper extends AbstractPackmasterCard {


    public final static String ID = makeID("WhipNSnapper");

    public WhipNSnapper() {
        super(ID, 2, AbstractCard.CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.GREEN.cpy()), 0.2F));
        addToBot(new WaitAction(0.2f));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        int r = AbstractDungeon.cardRandomRng.random(magicNumber);
        if (r > 0) {
            addToBot(new ApplyPowerAction(m,p, new WeakPower(m, r, false)));
        }
        r = AbstractDungeon.cardRandomRng.random(magicNumber);
        if (r > 0) {
            addToBot(new ApplyPowerAction(m,p, new VulnerablePower(m, r, false)));
        }
    }

    public void upp() {
        upgradeDamage(4);
    }
}
