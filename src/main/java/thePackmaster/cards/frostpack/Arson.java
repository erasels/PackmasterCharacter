package thePackmaster.cards.frostpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Arson extends AbstractFrostCard {
    public final static String ID = makeID("Arson");

    public Arson() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 14;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;

                for (AbstractCard c : AbstractDungeon.player.hand.group
                ) {
                    if (c.hasTag(SpireAnniversary5Mod.FROZEN)) {
                        c.modifyCostForCombat(-99);
                        if (CardModifierManager.hasModifier(c, FrozenMod.ID))
                            for (AbstractCardModifier mod : CardModifierManager.getModifiers(c, FrozenMod.ID)
                            ) {
                                CardModifierManager.removeSpecificModifier(c, mod, true);
                            }
                    }
                }
            }
        });

        /*
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;

                for (AbstractOrb o:AbstractDungeon.player.orbs
                     ) {
                    if (o instanceof Frost){
                        Wiz.att(new EvokeSpecificOrbAction(o));
                        Wiz.att(new ChannelAction(new Frostfire()));
                    }
                }
            }
        });

         */
    }

    public void upp() {
        upgradeDamage(4);
    }
}