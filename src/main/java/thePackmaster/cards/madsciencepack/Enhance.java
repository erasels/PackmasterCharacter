package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.GainBlockModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Enhance extends AbstractMadScienceCard {
    public final static String ID = makeID("Enhance");

    public Enhance() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 14;
        baseMagicNumber = magicNumber = 6;

    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
        addToBot(new FindCardForAddModifierAction(new GainBlockModifier(magicNumber),1,false, AbstractDungeon.player.hand, card->card.cost>-1));

    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(2);
    }
}