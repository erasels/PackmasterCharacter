package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonerspellspack.ClutchTeleportAction;
import thePackmaster.powers.summonerspellspack.GhostedPower;

public class ArtfulGhostCard extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ArtfulGhost");
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public ArtfulGhostCard() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyPowerAction(p, p, new GhostedPower(p, magicNumber), magicNumber));
    }
}
