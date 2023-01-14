package thePackmaster.cards.marisapack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.marisapack.BetterFireballEffect;
import thePackmaster.vfx.marisapack.MissileStrikeEffect;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ShootingEcho extends AbstractPackmasterCard {
    public final static String ID = makeID(ShootingEcho.class.getSimpleName());
    private static final String text = CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT[0];
    private static final int DMG = 9, UPG_DMG = 2;

    public ShootingEcho() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = DMG;
        exhaust = true;

        setBackgroundTexture("anniv5Resources/images/512/marisapack/" + type.name().toLowerCase(Locale.ROOT) + ".png",
                "anniv5Resources/images/1024/marisapack/" + type.name().toLowerCase(Locale.ROOT) + ".png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new MissileStrikeEffect(m.hb.cX, m.hb.cY, BetterFireballEffect.randomFlareColor()), Settings.ACTION_DUR_FASTER);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        Wiz.atb(new SelectCardsInHandAction(1, text, false, false, c -> true, list -> {
            ShootingEcho.this.resetAttributes();
            Wiz.makeInHand(ShootingEcho.this, list.size());

            list.forEach(c -> Wiz.hand().moveToExhaustPile(c));
            list.clear();
        }));
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
    }
}
