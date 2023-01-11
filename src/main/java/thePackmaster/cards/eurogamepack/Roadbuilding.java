package thePackmaster.cards.eurogamepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.eurogamepack.RoadbuildingPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Roadbuilding extends AbstractVPCard{
    public static final String ID = makeID("Roadbuilding");

    public Roadbuilding() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 4;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyToSelf(new RoadbuildingPower(AbstractDungeon.player, this.magicNumber));
    }
    public void upp() {
        this.upgradeMagicNumber(2);
    }
}