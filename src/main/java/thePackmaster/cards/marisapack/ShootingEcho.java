package thePackmaster.cards.marisapack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.marisapack.BetterFireballEffect;
import thePackmaster.vfx.marisapack.MissileStrikeEffect;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ShootingEcho extends AbstractPackmasterCard {
    public final static String ID = makeID(ShootingEcho.class.getSimpleName());
    private static final int DMG = 11, UPG_DMG = 3;

    public ShootingEcho() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = DMG;
        exhaust = true;

        setBackgroundTexture("anniv5Resources/images/512/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new MissileStrikeEffect(m.hb.cX, m.hb.cY, BetterFireballEffect.randomFlareColor()), Settings.ACTION_DUR_MED);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                if(!Wiz.hand().isEmpty()) {
                    Wiz.makeInHand(ShootingEcho.this);
                }
                isDone = true;
            }
        });
        Wiz.atb(new ExhaustAction(1, false, false, false));
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
    }
}
