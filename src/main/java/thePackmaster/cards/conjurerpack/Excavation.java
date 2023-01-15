package thePackmaster.cards.conjurerpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Excavation extends ConjurerCard
{
    public final static String ID = makeID("Excavation");
    private static final int BLOCK = 9;
    private static final int MAGIC = 2;


    public Excavation() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        AbstractDungeon.actionManager.addToBottom(new MoveCardsAction(p.drawPile, p.exhaustPile, magicNumber, (cards -> {
            for (AbstractCard c : cards)
            {
                if (!c.isEthereal) {
                    c.retain = true;
                }
            }
        })));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
