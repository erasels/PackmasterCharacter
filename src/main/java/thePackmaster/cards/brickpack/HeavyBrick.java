package thePackmaster.cards.brickpack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class HeavyBrick extends AbstractBrickCard {
    public final static String ID = makeID(HeavyBrick.class.getSimpleName());

    public HeavyBrick() {
        super(ID, -2, CardType.CURSE, CardRarity.SPECIAL, CardTarget.SELF, CardColor.CURSE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}