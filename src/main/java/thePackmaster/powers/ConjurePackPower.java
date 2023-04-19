package thePackmaster.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ConjurePackPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = makeID("ConjurePackPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private ArrayList<AbstractCard> cards = new ArrayList<>();
    private AbstractCardPack pack;

    public ConjurePackPower(AbstractCreature owner, int amount, AbstractCardPack pack) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);

        this.pack = pack;
        resetPackCards();
        updateDescription();
    }

    private void resetPackCards() {
        cards.clear();
        for (AbstractCard c : pack.cards) {
            if (!c.hasTag(AbstractCard.CardTags.HEALING) &&
                    (c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE)) {
                cards.add(c.makeCopy());
            }
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        AbstractCard c;

        //Not sure if there's any shenanigans regarding power modifications that could make this happen,
        //but just to be safe...
        if (cards.size() <= 1) {
            resetPackCards();
        }

        for (int i = 0; i < 2; i++) {
            c = Wiz.getRandomItem(cards);
            //c.modifyCostForCombat(-1);
            addToBot(new MakeTempCardInHandAction(c.makeCopy()));
            cards.remove(c);
        }

        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        if (pack != null) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + FontHelper.colorString(pack.name, "y") + DESCRIPTIONS[2];
        }
    }
}
