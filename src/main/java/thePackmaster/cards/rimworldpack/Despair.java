package thePackmaster.cards.rimworldpack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
@NoCompendium
@NoPools
public class Despair extends AbstractRimworldCard {
    public final static String ID = makeID(Despair.class.getSimpleName());

    public Despair() {
        super(ID, 2, CardType.CURSE, CardRarity.CURSE, CardTarget.SELF, CardColor.CURSE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}