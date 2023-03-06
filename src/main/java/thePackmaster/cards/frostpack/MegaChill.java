package thePackmaster.cards.frostpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.FROZEN;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class MegaChill extends AbstractFrostCard {
    public final static String ID = makeID("MegaChill");

    public MegaChill() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                for (AbstractCard c :AbstractDungeon.player.hand.group) {
                    if (c.hasTag(FROZEN)){
                        Wiz.atb(new ChannelAction(new Frost()));
                    } else {
                        if (!c.hasTag(SpireAnniversary5Mod.FROZEN)) Wiz.atb(new SimpleAddModifierAction(new FrozenMod(), c));
                    }
                }
            }
        });


    }

    public void upp() {

    }
}