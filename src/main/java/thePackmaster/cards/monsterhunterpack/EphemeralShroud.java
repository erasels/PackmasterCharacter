package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.vfx.SmokePuffEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EphemeralShroud extends AbstractMonsterHunterCard {
    public final static String ID = makeID("EphemeralShroud");

    private static final int MAGIC = 1;
    private static final int UPGRADED_COST = 0;

    public EphemeralShroud() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, magicNumber), magicNumber));
        addToBot(new VFXAction(new SmokePuffEffect(p.hb.cX, p.hb.cY)));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}