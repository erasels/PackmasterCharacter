package thePackmaster.cards.bellordpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Reverberation extends AbstractPackmasterCard {
    public final static String ID = makeID("Reverberation");
    // intellij stuff skill, self, common, , , 8, 3, , 

    public Reverberation() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.discardPile.contains(this)) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractDungeon.player.discardPile.moveToHand(Reverberation.this);
                }
            });
        }
    }

    public void upp() {
        upgradeBlock(3);
    }
}