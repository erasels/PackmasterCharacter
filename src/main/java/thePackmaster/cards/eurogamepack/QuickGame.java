package thePackmaster.cards.eurogamepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.eurogamepack.QuickGamePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class QuickGame extends AbstractVPCard{
    public static final String ID = makeID("QuickGame");

    public QuickGame() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyToSelf(new QuickGamePower(AbstractDungeon.player, this.magicNumber));
    }
    public void upp() {
            this.isInnate = true;
    }
}
