package thePackmaster.cards.alignmentpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.arcanapack.AbstractAstrologerCard;
import thePackmaster.vfx.alignmentpack.FlashImageEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Conjunction extends AbstractAstrologerCard {
    public final static String ID = makeID("Conjunction");

    public Conjunction() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseBlock = 9;
        isEthereal = true;
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new VFXAction(new FlashImageEffect(this.portrait)));
        this.applyPowers(); //Powers are not applied before the action is added otherwise
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeBlock(3);
    }
}