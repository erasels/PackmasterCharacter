package thePackmaster.cards.rippack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.cardmodifiers.rippack.RippableModifier;
import thePackmaster.vfx.rippack.ArtAttackArtEffect;
import thePackmaster.vfx.rippack.ArtAttackTextEffect;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.NONE;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.isArtCard;

public class ArtAttack extends AbstractRipCard {
    public final static String ID = makeID("ArtAttack");

    public ArtAttack() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 12;
        CardModifierManager.addModifier(this, new RippableModifier());
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //YAY RAINBOW
        if(isArtCard(this)) {
            AbstractGameEffect rainbowEffect = ArtAttackArtEffect.RainbowBoomerang(m);

            atb(new SFXAction(makeID("RipPack_Yay")));
            atb(new VFXAction(rainbowEffect));
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    if (rainbowEffect.isDone) {
                        isDone = true;
                    }
                }
            });
            atb(new SFXAction(makeID("RipPack_Harp")));
            atb(new LoseHPAction(m, m, magicNumber));
            atb(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, NONE)));
        } else { //RAINBOW SMASH
            AbstractGameEffect rainbowSplash = new ArtAttackTextEffect(m.hb.cX, m.hb.cY);
            atb(new VFXAction(rainbowSplash));
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    if (rainbowSplash.isDone) {
                        isDone = true;
                    }
                }
            });
            atb(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
    }
}
