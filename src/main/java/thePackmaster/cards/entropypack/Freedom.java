package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Freedom extends AbstractPackmasterCard {
    public final static String ID = makeID("Freedom");
    // intellij stuff skill, self, uncommon, , , 14, 4, , 

    public Freedom() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 14;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerOnManualDiscard() {
        applyPowers();
        forAllMonstersLiving(
                (m)->att(new GainBlockAction(m, AbstractDungeon.player, this.block))
        );
        addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
    }

    public void upp() {
        upgradeBlock(4);
    }
}