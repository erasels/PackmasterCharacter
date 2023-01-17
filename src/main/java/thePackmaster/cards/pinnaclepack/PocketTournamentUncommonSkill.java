package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.pinnaclepack.actions.MinigameAction;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PocketTournamentUncommonSkill extends AbstractPinnacleCard {

    public final static String ID = makeID("PocketTournamentUncommonSkill");
    private static final int MAGIC = 0;
    private static final int UPGRADE_MAGIC = 2;

    public PocketTournamentUncommonSkill() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new MinigameAction(p, this.freeToPlayOnce, this.energyOnUse, this.upgraded));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

}
