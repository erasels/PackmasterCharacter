package thePackmaster.cards.marisapack;

import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import thePackmaster.actions.DrawToHandAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EscapeVelocity extends AbstractMarisaCard implements OnChargeUpCard {
    public final static String ID = makeID(EscapeVelocity.class.getSimpleName());
    private static final int BLK = 8, UPG_BLK = 2;

    public EscapeVelocity() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = block = BLK;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new ExplosionSmallEffect(p.hb.cX, p.hb.y), Settings.ACTION_DUR_XFAST);
        blck();
    }

    @Override
    public void onChargeUpConsumed(AbstractCard card) {
        addToBot(new DrawToHandAction(this));
        addToBot(new DiscardToHandAction(this));
    }

    public void upp() {
        upgradeBlock(UPG_BLK);
    }
}
