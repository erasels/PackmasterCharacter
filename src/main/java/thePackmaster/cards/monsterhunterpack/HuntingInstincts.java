package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HuntingInstincts extends AbstractMonsterHunterCard {
    public final static String ID = makeID("HuntingInstincts");

    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 1;

    public HuntingInstincts() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (mo.type == AbstractMonster.EnemyType.BOSS || mo.type == AbstractMonster.EnemyType.ELITE){
                addToBot(new GainBlockAction(p, block*2));
                return;
            }
        }
        addToBot(new GainBlockAction(p, block));
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}