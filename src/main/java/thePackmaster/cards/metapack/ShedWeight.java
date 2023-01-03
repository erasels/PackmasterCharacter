package thePackmaster.cards.metapack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.green.Survivor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import thePackmaster.actions.metapack.ShedWeightAction;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ShedWeight extends AbstractPackmasterCard {
    public final static String ID = makeID("ShedWeight");

    public ShedWeight() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
        this.addToBot(new ShedWeightAction(p, p, false));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
