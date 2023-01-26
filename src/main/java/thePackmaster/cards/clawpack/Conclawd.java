package thePackmaster.cards.clawpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Conclawd extends AbstractClawCard {
    public final static String ID = makeID("Conclawd");

    public Conclawd() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 2;
        tags.add(CLAW);
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster m2 : Wiz.getEnemies()
        ) {
            this.addToBot(new VFXAction(new ClawEffect(m2.hb.cX, m2.hb.cY, Color.PURPLE, Color.WHITE), 0.1F));
        }

        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        ClawUp(magicNumber);

        this.addToBot(new PressEndTurnButtonAction());
    }

    public void upp() {
        upgradeDamage(4);
    }
}