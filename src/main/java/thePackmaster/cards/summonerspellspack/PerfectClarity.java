package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class PerfectClarity extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("PerfectClarity");
    private static final int COST = 2;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;


    public PerfectClarity() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        magicNumber = baseMagicNumber = MAGIC;
        cardsToPreview = new Miracle();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i)
            this.addToBot(new MakeTempCardInHandAction(new Miracle()));
    }
}
