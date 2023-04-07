package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.monsterhunterpack.MarkEnemyAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.monsterhunterpack.HuntersMark;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HuntingContract extends AbstractMonsterHunterCard {
    public final static String ID = makeID("HuntingContract");

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 2;

    public HuntingContract() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
        this.isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MarkEnemyAction(p, m, magicNumber));
        addToBot(new ApplyPowerAction(m, p, new HuntersMark(m, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}