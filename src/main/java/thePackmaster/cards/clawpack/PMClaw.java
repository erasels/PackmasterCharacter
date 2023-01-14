package thePackmaster.cards.clawpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import com.sun.javafx.tk.ImageLoader;
import thePackmaster.SpireAnniversary5Mod;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PMClaw extends AbstractClawCard {
    public final static String ID = makeID("PMClaw");

    public PMClaw() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, CardColor.BLUE);
        //Does this do a Card Art Roller first, then override it?  Probably.  Might be worth having a 'skip art roller' bool?
        //Seems better than needing to copy Claw's base art as a new file.
        TextureAtlas atlas = ReflectionHacks.getPrivate(this, AbstractCard.class, "cardAtlas");
        this.portrait = atlas.findRegion("blue/attack/claw");
        baseDamage = 3;
        baseMagicNumber = magicNumber = 2;
        tags.add(CLAW);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.CYAN, Color.WHITE), 0.1F));
        }
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        ClawUp(magicNumber);
    }

    public void upp() {
        upgradeDamage(2);
    }
}