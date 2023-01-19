package thePackmaster.cards.warriorpack;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.warriorpack.FrontDamage;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.forAllMonstersLiving;

public class Chained extends AbstractWarriorCard {

    public final static String ID = makeID(Chained.class.getSimpleName());

    private static final int COST = 1;

    public Chained(){
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL);
        baseBlock = 9;
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, block));
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters)
            if (!q.isDeadOrEscaped())
                atb(new GainBlockAction(q, p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        upMagic(-1);
    }
}
