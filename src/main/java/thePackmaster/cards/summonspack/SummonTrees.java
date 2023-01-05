package thePackmaster.cards.summonspack;

import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.summonspack.TreeBlockadePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class SummonTrees extends AbstractPackmasterCard {
    public final static String ID = makeID(SummonTrees.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 4;
    private static final int UPGRADE_BLOCK = 1;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    public SummonTrees() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new TreeBlockadePower(adp(), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upMagic(UPGRADE_MAGIC);
    }
}
