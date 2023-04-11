package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.actions.summonerspellspack.ClutchTeleportAction;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.packs.SummonerSpellsPack;

import java.util.ArrayList;

public class ClutchTeleport extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ClutchTeleport");
    private static final int COST = 3;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public ClutchTeleport() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> packCards = new ArrayList<>();

        for (int i = 0; i < this.magicNumber; i++) {
            AbstractCard c = SpireAnniversary5Mod.getRandomCardFromPack(new SummonerSpellsPack()).makeStatEquivalentCopy();
            c.setCostForTurn(0);
            addToBot(new MakeTempCardInHandAction(c, 1));
        }

    }
}
