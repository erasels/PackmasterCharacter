package thePackmaster.cards.secretlevelpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.secretlevelpack.SpecialCardGlowCheckPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Conglaturation extends AbstractSecretLevelCard {
    public final static String ID = makeID("Conglaturation");
    // intellij stuff attack, enemy, uncommon, 9, 3, , , , 

    public Conglaturation() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (SpecialCardGlowCheckPatch.playedGlowingCardThisTurn) {
            atb(new DrawCardAction(1));
            atb(new GainEnergyAction(1));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = SpecialCardGlowCheckPatch.playedGlowingCardThisTurn ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(3);
    }
}