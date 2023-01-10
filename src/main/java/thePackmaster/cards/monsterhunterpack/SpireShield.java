package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SpireShield extends AbstractMonsterHunterCard {
    public final static String ID = makeID("SpireShield");

    public static final int BLOCK = 30;
    public static final int UPG_BLOCK = 10;

    public SpireShield() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseBlock = block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}