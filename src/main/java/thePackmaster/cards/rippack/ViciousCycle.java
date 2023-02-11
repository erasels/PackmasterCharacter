package thePackmaster.cards.rippack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.rippack.RippableModifier;
import thePackmaster.vfx.rippack.SlashDiagonalOppositeEffect;

import static thePackmaster.SpireAnniversary5Mod.cardsRippedThisTurn;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ViciousCycle extends AbstractRipCard {
    public final static String ID = makeID("ViciousCycle");


    public ViciousCycle() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        CardModifierManager.addModifier(this, new RippableModifier());
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + cardsRippedThisTurn;
        if (cardsRippedThisTurn == 1) {
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);

        for(int i = 1; i <= cardsRippedThisTurn; i++) {
            switch(i % 4) {
                case 1:
                    atb(new VFXAction(new SlashDiagonalOppositeEffect(m.hb.cX, m.hb.cY, false)));
                    atb(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                    break;
                case 2:
                    dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
                    break;
                case 3:
                    dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
                    break;
                default:
                    dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
                    break;
            }
        }
    }
}
