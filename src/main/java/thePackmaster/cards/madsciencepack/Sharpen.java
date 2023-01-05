package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.AddDamageModifier;
import thePackmaster.cards.madsciencepack.AbstractMadScienceCard;
import thePackmaster.cards.dimensiongatepack.AbstractDimensionalCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sharpen extends AbstractMadScienceCard {
    public final static String ID = makeID("Sharpen");

    public Sharpen() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 5;

    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        addToBot(new FindCardForAddModifierAction(new AddDamageModifier(magicNumber),1,true, AbstractDungeon.player.hand, card->card.type==CardType.ATTACK));

    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}