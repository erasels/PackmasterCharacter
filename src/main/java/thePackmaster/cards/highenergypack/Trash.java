package thePackmaster.cards.highenergypack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class Trash extends AbstractHighEnergyCard {
    public final static String ID = makeID("Trash");
    // intellij stuff skill, self, common, , , , , , 

    public Trash() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractCard q = getLeftmostCard();
                if (q != null)
                    att(new ExhaustSpecificCardAction(q, AbstractDungeon.player.hand, false));
            }
        });
        atb(new GainEnergyAction(1));
    }

    @Override
    public void hover() {
        super.hover();
        if (Wiz.isInCombat()) {
            AbstractCard q = getLeftmostCard();
            if (q != null) {
                q.glowColor = Color.RED.cpy();
                q.beginGlowing();
            }
        }
    }

    @Override
    public void unhover() {
        super.unhover();
        if (Wiz.isInCombat()) {
            AbstractCard q = getLeftmostCard();
            if (q != null) {
                q.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
                q.triggerOnGlowCheck();
                AbstractDungeon.player.hand.applyPowers();
            }
        }
    }


    private AbstractCard getLeftmostCard() {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractCard q : p.hand.group) {
            if (q != this) {
                return q;
            }
        }
        return null;
    }

    public void upp() {
        selfRetain = true;
    }
}