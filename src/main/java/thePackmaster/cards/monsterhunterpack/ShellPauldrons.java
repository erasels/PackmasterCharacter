package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.monsterhunterpack.GlassArmorPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ShellPauldrons extends AbstractMonsterHunterCard {
    public final static String ID = makeID("ShellPauldrons");

    private static final int MAGIC = 6;

    public ShellPauldrons() {
        super(ID, 1, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        this.isInnate = false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        this.isInnate = true;
    }
}