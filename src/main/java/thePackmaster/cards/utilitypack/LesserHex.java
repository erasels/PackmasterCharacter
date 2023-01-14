package thePackmaster.cards.utilitypack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.vfx.utilitypack.HexEffect;

public class LesserHex extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("LesserHex");
    private static final int COST = 0;
    private static final int STATS = 1;

    public LesserHex() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = STATS;
        this.exhaust = true;
        this.cardsToPreview = new GreaterHex();
    }

    @Override
    public void upp() {
        this.cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new HexEffect(m.hb.cX, m.hb.cY, STATS), 0.6F));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false)));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false)));
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber)));
        GreaterHex c = new GreaterHex();
        if (this.upgraded) {
            c.upgrade();
        }
        this.addToBot(new MakeTempCardInDiscardAction(c, 1));
    }
}
