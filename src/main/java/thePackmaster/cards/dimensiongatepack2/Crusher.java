package thePackmaster.cards.dimensiongatepack2;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGrift;
import thePackmaster.util.creativitypack.onGenerateCardMidcombatInterface;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Crusher extends AbstractDimensionalCardGrift implements onGenerateCardMidcombatInterface {
    public final static String ID = makeID("Crusher");

    private static final int COST = 5;
    public Crusher() {
        super(ID, COST, CardRarity.UNCOMMON, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 20;
        selfRetain = true;

    }

    @Override
    public void onCreateCard(AbstractCard card) {
        if (card != this && Wiz.p().hand.group.contains(this)) {
            if (Wiz.getLogicalCardCost(this) > 0) {
                modifyCostForCombat(-1);
                this.flash(Color.GREEN.cpy());
            }
        }
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY), 0.4F));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Crusher.this.modifyCostForCombat(COST-Crusher.this.cost);
                Crusher.this.isCostModified=false;
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(5);
    }
}