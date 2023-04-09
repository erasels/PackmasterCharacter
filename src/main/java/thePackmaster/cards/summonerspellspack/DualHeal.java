package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonerspellspack.GhostedPower;

public class DualHeal extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("DuoHeal");
    private static final int COST = 1;
    private static final int MAGIC = 7;
    private static final int UPG_MAGIC = 3;
    private static final int MAGIC2 = 1;


    public DualHeal() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        exhaust = true;
        magicNumber = baseMagicNumber = MAGIC;
        secondMagic = baseSecondMagic = MAGIC2;
        tags.add(CardTags.HEALING);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, this.magicNumber, 0));
        addToBot(new HealAction(m, p, this.magicNumber, 1));

        addToBot(new ApplyPowerAction(p, p, new GhostedPower(p, this.secondMagic), this.secondMagic));
    }
}
