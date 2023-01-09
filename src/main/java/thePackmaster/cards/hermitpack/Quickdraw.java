package thePackmaster.cards.hermitpack;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Quickdraw extends AbstractHermitCard {
    public final static String ID = makeID("Quickdraw");

    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int UPGRADE_PLUS_CARD = 1;
    private static final int DRAWLESS = 1;

    public Quickdraw() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT)
        );
        this.addToBot(new DrawCardAction(magicNumber));

        DrawReductionPower pow = new DrawReductionPower(p,DRAWLESS);
        ReflectionHacks.setPrivate(pow,DrawReductionPower.class,"justApplied",false);

        this.addToBot(new ApplyPowerAction(p, p, pow, DRAWLESS));
    }

    @Override
    public void upp() {
            upgradeMagicNumber(UPGRADE_PLUS_CARD);
            upgradeDamage(UPGRADE_PLUS_DMG);
    }
}