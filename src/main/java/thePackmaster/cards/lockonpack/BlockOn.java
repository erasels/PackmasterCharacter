package thePackmaster.cards.lockonpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.lockonpack.GlockOnModifier;
import thePackmaster.patches.hermitpack.EnumPatch;
import thePackmaster.powers.lockonpack.BlockOnPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BlockOn extends AbstractLockonCard {

    public final static String ID = makeID(BlockOn.class.getSimpleName());

    public BlockOn() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 8;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doBlk(block);
        addToBot(new ApplyPowerAction(p, p, new BlockOnPower(p, magicNumber)));
    }
}
