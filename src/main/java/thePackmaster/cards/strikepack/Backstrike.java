package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.strikepack.AddStrikeTagModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Backstrike extends AbstractStrikePackCard {
    public final static String ID = makeID("Backstrike");

    private static final int BLOCK_VALUE = 12;
    private static final int UPGRADE_PLUS_BLOCK_VALUE = 4;
    public Backstrike() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        this.tags.add(CardTags.STRIKE);
        baseBlock = BLOCK_VALUE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (AbstractCard c: p.hand.group
             ) {
            if (c.type == CardType.ATTACK && !c.hasTag(CardTags.STRIKE)) {
                addToBot(new SimpleAddModifierAction(new AddStrikeTagModifier(0), c));
            }
        }
    }

    public void upp() {
        this.upgradeBlock(UPGRADE_PLUS_BLOCK_VALUE);
    }
}