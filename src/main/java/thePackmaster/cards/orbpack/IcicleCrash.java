package thePackmaster.cards.orbpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Frost;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class IcicleCrash extends AbstractOrbCard {
    public final static String ID = makeID("IcicleCrash");
    // intellij stuff attack, enemy, uncommon, 12, 5, , , , 

    public IcicleCrash() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 13;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        final int amt = this.magicNumber;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                for (AbstractOrb orb : p.orbs) {
                    if (orb instanceof EmptyOrbSlot) {
                        for (int i = 0; i < amt; ++i)
                            att(new ChannelAction(new Frost()));
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}