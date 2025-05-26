package thePackmaster.cards.rimworldpack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.BlueCandle;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoPools
public class Despair extends AbstractRimworldCard {
    public final static String ID = makeID(Despair.class.getSimpleName());

    public Despair() {
        super(ID, 2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, CardColor.CURSE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasRelic(BlueCandle.ID))
            addToBot(new LoseHPAction(p, p, 1, AbstractGameAction.AttackEffect.FIRE));
    }

    public void upp() {
    }
}