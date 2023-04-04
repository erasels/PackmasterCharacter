package thePackmaster.cards.artificerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.artificerpack.BlockEnchantModifier;

public class SpellStone extends AbstractArtificerCard {

    public static final String ID = SpireAnniversary5Mod.makeID("SpellStone");

    public SpellStone(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 3;
        baseDamage = 9;
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
