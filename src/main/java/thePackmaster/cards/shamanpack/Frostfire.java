package thePackmaster.cards.shamanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.shamanpack.IgnitePower;

public class Frostfire extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Frostfire");
    private static final int COST = 0;
    private static final int IGNITE = 1;
    private static final int UPGRADE_IGNITE = 1;
    private static final int WEAK = 1;
    private static final int UPGRADE_WEAK = 1;

    public Frostfire() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = IGNITE;
        this.secondMagic = this.baseSecondMagic = WEAK;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_IGNITE);
        this.upgradeSecondMagic(UPGRADE_WEAK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster unused) {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDead && !m.isDying) {
                this.addToBot(new ApplyPowerAction(m, p, new IgnitePower(m, this.magicNumber)));
                this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.secondMagic, false)));
            }
        }
    }
}
