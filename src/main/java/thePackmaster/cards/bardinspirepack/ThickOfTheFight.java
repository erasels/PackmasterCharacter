package thePackmaster.cards.bardinspirepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.bardinspirepack.ThickOfTheFightAction;
import thePackmaster.util.creativitypack.onGenerateCardMidcombatInterface;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ThickOfTheFight extends AbstractBardCard implements onGenerateCardMidcombatInterface
{
    public final static String ID = makeID("ThickOfTheFight");
    private static final int COST = 3;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_DAMAGE = 3;

    private int currentMonsterCount = 0;

    public ThickOfTheFight()
    {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
    }

    public void monstersChanged(int monsterCount)
    {
        updateCost(currentMonsterCount - monsterCount);
        currentMonsterCount = monsterCount;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int livingMonsters = 0;
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDeadOrEscaped()) {
                ++livingMonsters;
            }
        }

        atb(new ThickOfTheFightAction(
                AbstractDungeon.getMonsters().getRandomMonster(true),
                new DamageInfo(p, baseDamage),
                livingMonsters
        ));
    }

    @Override
    public void upp()
    {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeSecondDamage(UPGRADE_DAMAGE);
    }

    @Override
    public AbstractCard makeStatEquivalentCopy()
    {
        ThickOfTheFight ret = (ThickOfTheFight) super.makeStatEquivalentCopy();
        ret.currentMonsterCount = currentMonsterCount;
        return ret;
    }

    @Override
    public void onCreateThisCard() {
        monstersChanged(Wiz.getEnemies().size());
    }
}
