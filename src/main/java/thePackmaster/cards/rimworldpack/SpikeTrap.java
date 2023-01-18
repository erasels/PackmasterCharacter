package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.SpikeTrapPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SpikeTrap extends AbstractRimworldCard {
    public final static String ID = makeID(SpikeTrap.class.getSimpleName());

    public SpikeTrap() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        block = baseBlock = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new SpikeTrapPower(p, block));
    }

    public void upp() {
        upgradeBlock(4);
    }
}