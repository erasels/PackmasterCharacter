package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.monsterhunterpack.PerfectGuardPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PerfectDodge extends AbstractMonsterHunterCard {
    public final static String ID = makeID("PerfectDodge");

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 4;

    public PerfectDodge() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new PerfectGuardPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}