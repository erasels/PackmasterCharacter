package thePackmaster.cards.quietpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Wisdom extends AbstractQuietCard {
    public final static String ID = makeID("Wisdom");

    public Wisdom() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
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
        upMagic(1);
    }
}


