package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonerspellspack.GhostedPower;

public class SonicBarrier extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("SonicBarrier");
    private static final int COST = 2;
    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 2;

    public SonicBarrier() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (isOverextended())
            addToBot(new ApplyPowerAction(p, p, new GhostedPower(p, magicNumber), magicNumber));
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = isOverextended() ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
}
