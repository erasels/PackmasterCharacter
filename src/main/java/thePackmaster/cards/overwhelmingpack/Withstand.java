package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ReinforcedBodyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Withstand extends AbstractOverwhelmingCard {
    public final static String ID = makeID("Withstand");

    public Withstand() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseBlock = this.block = 4;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int blck = this.block, threshold = this.magicNumber;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                for (AbstractCard c : p.hand.group) {
                    if (Wiz.getLogicalCardCost(c) <= threshold) {
                        addToTop(new GainBlockAction(p, p, blck));
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeBlock(1);
    }
}