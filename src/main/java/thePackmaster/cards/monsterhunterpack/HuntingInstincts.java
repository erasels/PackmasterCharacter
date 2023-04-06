package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HuntingInstincts extends AbstractMonsterHunterCard {
    public final static String ID = makeID("HuntingInstincts");

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;

    public HuntingInstincts() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (hasHuntTarget()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        if(hasHuntTarget()) {
            block *= 2;
            isBlockModified = true;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block * (hasHuntTarget()? 2 : 1)));
    }

    public boolean hasHuntTarget() {
        for (AbstractMonster mo : Wiz.getEnemies()) {
            if (mo.type == AbstractMonster.EnemyType.BOSS || mo.type == AbstractMonster.EnemyType.ELITE) {
                return true;
            }
        }
        return false;
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}