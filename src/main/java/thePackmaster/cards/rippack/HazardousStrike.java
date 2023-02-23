package thePackmaster.cards.rippack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.actions.rippack.ExhaustRandomNonArtCardsAction;
import thePackmaster.cardmodifiers.rippack.RippableModifier;
import thePackmaster.vfx.rippack.HazardousStrikeEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class HazardousStrike extends AbstractRipCard implements OnRipInterface {
    public final static String ID = makeID("HazardousStrike");


    public HazardousStrike() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 18;
        baseMagicNumber = magicNumber = 2;
        tags.add(CardTags.STRIKE);
        CardModifierManager.addModifier(this, new RippableModifier());
    }

    @Override
    public void upp() {
        upgradeDamage(8);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameEffect hyyah = HazardousStrikeEffect.JumpSlash(m);
        atb(new VFXAction(hyyah));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (hyyah.isDone) {
                    isDone = true;
                }
            }
        });
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public void onRip() {
        att(new ExhaustRandomNonArtCardsAction(magicNumber)); //att to it runs before making the new text/art cards in hand
        att(new VFXAction(AbstractDungeon.player, HazardousStrikeEffect.CutCardsInHand(), 0.25f,true));
    }
}
