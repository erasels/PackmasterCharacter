package thePackmaster.cards.weaponspack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.RipAndTearEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PirateHook extends AbstractWeaponsPackCard {
    public static final String ID = makeID("PirateHook");

    private static final int COST = 1;
    private static final int ATTACK_DMG = 7;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 2;

    public PirateHook() {
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);

        this.baseDamage = this.damage = ATTACK_DMG;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int hits = this.damage >= 10 ? 2 : 1;
        for (int i = 0; i < hits; i++) {
            addToBot(new VFXAction(new RipAndTearEffect(m.hb.cX, m.hb.cY, Color.RED, Color.GOLD)));
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = this.damage >= 10 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
        }
    }
}
