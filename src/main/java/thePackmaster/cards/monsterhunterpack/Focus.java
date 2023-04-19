package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.monsterhunterpack.MarkEnemyAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.monsterhunterpack.HuntersMark;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Focus extends AbstractMonsterHunterCard {
    public final static String ID = makeID("Focus");

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;

    public Focus() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new MarkEnemyAction(p, m, magicNumber));
        addToBot(new ApplyPowerAction(m, p, new HuntersMark(m, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
        upgradeMagicNumber(UPG_MAGIC);
    }
}