package thePackmaster.cards.alignmentpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.arcanapack.AbstractAstrologerCard;
import thePackmaster.powers.alignmentpack.TrinePower;
import thePackmaster.vfx.alignmentpack.FlashImageEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class Trine extends AbstractAstrologerCard {
    public final static String ID = makeID("Trine");

    public Trine() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        isEthereal = true;
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new VFXAction(new FlashImageEffect(this.portrait)));
        applyToSelf(new TrinePower(AbstractDungeon.player, 1));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeBaseCost(1);
    }
}