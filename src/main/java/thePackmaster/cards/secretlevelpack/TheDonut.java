package thePackmaster.cards.secretlevelpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.RepeatCardAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class TheDonut extends AbstractSecretLevelCard {
    public final static String ID = makeID("TheDonut");
    // intellij stuff attack, all_enemy, rare, 10, 2, , , , 

    public TheDonut() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 9;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        AbstractCard q = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy();
        if (upgraded) q.upgrade();
        atb(new RepeatCardAction(q));
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = GOLD_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(1);
    }
}