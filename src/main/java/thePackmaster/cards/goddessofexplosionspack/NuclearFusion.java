package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NuclearFusion extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("NuclearFusion");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("HandpickUI"));

    private static final int MAGIC = 0;
    private static final int MAGIC_UP = 1;

    public NuclearFusion() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
        cardsToPreview = new Burn();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new EasyXCostAction(this,(effect, params) -> {
            Wiz.att(new MakeTempCardInHandAction(new Burn(), effect));
            Wiz.att(new HandSelectAction(1, card -> card.cost <= effect+params[0],
                    list -> {
                        for (AbstractCard c : list) {
                            AbstractCard tmp = c.makeStatEquivalentCopy();
                            tmp.purgeOnUse = true;

                            // Play real card and then the copy.
                            Wiz.att(new NewQueueCardAction(tmp, true, false, true));
                            Wiz.att(new NewQueueCardAction(c, true, false, true));
                        }
                    },null, uiStrings.TEXT[0],false,false,false)
            );
            return true;
        },magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(MAGIC_UP);
    }
}
