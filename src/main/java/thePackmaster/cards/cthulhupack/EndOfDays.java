package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.blue.Darkness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.cthulhupack.AllSeeingPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EndOfDays extends AbstractCthulhuCard {
    public final static String ID = makeID("EndOfDays");


    public EndOfDays() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new IncreaseMaxOrbAction(magicNumber));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0; i < AbstractDungeon.player.maxOrbs; i++) {
                    addToBot(new ChannelAction(new Dark()));
                }
                isDone = true;
            }
        });
    }

    public void upp() {
            upgradeMagicNumber(1);
    }
}