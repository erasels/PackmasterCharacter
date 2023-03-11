package thePackmaster.cards.frostpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

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
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (CardModifierManager.hasModifier(c, FrozenMod.ID)) {
                        Wiz.atb(new ChannelAction(new Frost()));
                    } else {
                        Wiz.atb(new SimpleAddModifierAction(new FrozenMod(), c, false));
                    }
                }
            }
        });


    }

    public void upp() {

    }
}