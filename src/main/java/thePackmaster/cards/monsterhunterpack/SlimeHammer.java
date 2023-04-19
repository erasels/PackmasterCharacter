package thePackmaster.cards.monsterhunterpack;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.monsterhunterpack.DelayedDamagePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SlimeHammer extends AbstractMonsterHunterCard {
    public final static String ID = makeID("SlimeHammer");

    public static final int DAMAGE = 32;

    public SlimeHammer() {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int roll = MathUtils.random(1);
        if (roll == 0) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_SLIMEBOSS_1A"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_SLIMEBOSS_1B"));
        }
        addToBot(new ApplyPowerAction(m, p, new DelayedDamagePower(m, damage), damage));
        if (upgraded){
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1, false), 1));
        }
    }

    public void upp() {
    }
}