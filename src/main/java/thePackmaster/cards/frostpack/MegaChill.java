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
import thePackmaster.patches.psychicpack.occult.OccultPatch;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MegaChill extends AbstractFrostCard {
    public final static String ID = makeID("MegaChill");

    public MegaChill() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if(MegaChill.this.upgraded) {
                        if (!CardModifierManager.hasModifier(c, FrozenMod.ID) && !OccultPatch.isUnplayable(Wiz.p(), c)) {
                            Wiz.atb(new SimpleAddModifierAction(new FrozenMod(), c, false));
                            channelFrost();
                        } else if(CardModifierManager.hasModifier(c, FrozenMod.ID)) {
                            channelFrost();
                        }
                    } else {
                        if (CardModifierManager.hasModifier(c, FrozenMod.ID) && !OccultPatch.isUnplayable(Wiz.p(), c)) {
                            channelFrost();
                        } else {
                            Wiz.atb(new SimpleAddModifierAction(new FrozenMod(), c, false));
                        }
                    }
                }
            }
        });
    }

    private void channelFrost() {
        for (int i = 0; i < magicNumber; i++) {
            Wiz.atb(new ChannelAction(new Frost()));
        }
    }

    public void upp() {

    }
}