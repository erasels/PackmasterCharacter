package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonerspellspack.FlashAction;

public class Flash extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Flash");
    private static final int COST = 1;
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public Flash() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            this.addToBot(new FlashAction(p));
    }
}
