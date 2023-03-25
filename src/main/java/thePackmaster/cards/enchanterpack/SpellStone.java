package thePackmaster.cards.enchanterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.enchanterpack.BlockEnchantModifier;

import java.util.ArrayList;

public class SpellStone extends AbstractEnchanterCard {

    public static final String ID = SpireAnniversary5Mod.makeID("SpellStone");

    public SpellStone(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 4;
        baseDamage = 8;
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (AbstractCard c : getNeighbors()) {
            addToBot(new SimpleAddModifierAction(new BlockEnchantModifier(magicNumber), c));
        }
    }
}
