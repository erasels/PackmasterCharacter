package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;

public class WitheringExhaust extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("WitheringExhaust");
    private static final int COST = 1;
    private static final int MAGIC = 7;
    private static final int UPG_MAGIC = 3;
    private static final int MAGIC_TWO = 2;
    private static final int UPG_MAGIC_TWO = 1;

    public WitheringExhaust() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.secondMagic = this.baseSecondMagic = MAGIC_TWO;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        upgradeSecondMagic(UPG_MAGIC_TWO);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        if (m != null && !m.hasPower(ArtifactPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, this.magicNumber), this.magicNumber));
        }

        if (AbstractDungeon.player.hand.group.size() >= 6)
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.secondMagic, false), this.secondMagic));
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.player.hand.group.size() >= 6 ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }
}
