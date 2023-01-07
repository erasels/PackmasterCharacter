package thePackmaster.cards.quietpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Wisdom extends AbstractPackmasterCard {
    public final static String ID = makeID("Wisdom");

    public Wisdom() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
        if (upgraded)
            atb(new GainEnergyAction(1));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (!AbstractDungeon.player.hand.isEmpty()) {
                    this.addToBot(new GamblingChipAction(AbstractDungeon.player, true));
                }
                isDone = true;
            }
        });

    }

    @Override
    public void upp() {

    }
}


