package thePackmaster.cards.bardinspirepack;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class Shifting extends AbstractBardCard
{
    public final static String ID = makeID("Shifting");
    private static final int COST = 3;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_DAMAGE = 4;
    private static final int BLOCK = 12;
    private static final int UPGRADE_BLOCK = 4;

    public Shifting()
    {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new ShiftingChoiceCard("Attack", AbstractCard.CardType.ATTACK, m, damage, -1));
        choices.add(new ShiftingChoiceCard("Block", AbstractCard.CardType.SKILL, m, -1, block));
        atb(new ChooseOneAction(choices));

        atb(new WaitAction(0.5f));

        choices = new ArrayList<>();
        choices.add(new ShiftingChoiceCard("Attack", AbstractCard.CardType.ATTACK, m, damage, -1));
        choices.add(new ShiftingChoiceCard("Block", AbstractCard.CardType.SKILL, m, -1, block));
        atb(new ChooseOneAction(choices));
    }

    @Override
    public void upp()
    {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeBlock(UPGRADE_BLOCK);
    }

    @AutoAdd.Ignore
    private static class ShiftingChoiceCard extends AbstractBardCard
    {
        private static final int COST = -2;
        private final String baseID;
        private final AbstractMonster target;

        ShiftingChoiceCard(String id, AbstractCard.CardType type, AbstractMonster targetMonster, int damageAmt, int blockAmt)
        {
            super(makeID(id), COST, type, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE);

            baseID = id;

            target = targetMonster;
            baseDamage = damageAmt;
            baseBlock = blockAmt;
        }

        private static String makeID(String id)
        {
            return SpireAnniversary5Mod.makeID("Shifting" + id);
        }

        @Override
        public void onChoseThisOption()
        {
            if (baseBlock >= 0) {
                att(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, baseBlock));
            }
            if (baseDamage >= 0) {
                att(new DamageAction(target, new DamageInfo(AbstractDungeon.player, baseDamage, damageTypeForTurn)));
            }
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
        {
        }

        @Override
        public void upp()
        {
        }

        @Override
        public AbstractCard makeCopy()
        {
            return new ShiftingChoiceCard(baseID, type, target, baseDamage, baseBlock);
        }
    }
}
