package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.shamanpack.IgnitePower;

public class DualHeal extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("DualHeal");
    private static final int COST = 0;
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;


    public DualHeal() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        exhaust = true;
        magicNumber = baseMagicNumber = MAGIC;
        tags.add(CardTags.HEALING);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, this.magicNumber, 0));
        if (m != null && !m.hasPower(IgnitePower.POWER_ID))
            addToBot(new HealAction(m, p, this.magicNumber,0.2f));
    }
}
