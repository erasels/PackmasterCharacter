package thePackmaster.cards.odditiespack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.hats.Hats;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.packs.CoreSetPack;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.makeInHand;

public class StylePoints extends AbstractOdditiesCard {
    public final static String ID = makeID("StylePoints");
    // intellij stuff skill, self, uncommon, , , 8, 3, , 

    public StylePoints() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new RemoveSpecificPowerAction(p, p, WeakPower.POWER_ID));
        AbstractCardPack pack = SpireAnniversary5Mod.packsByID.get(CoreSetPack.ID);
        if (Hats.currentHat != null) {
            pack = SpireAnniversary5Mod.packsByID.get(Hats.currentHat);
        }
        AbstractCard tar = CardLibrary.getCard(Wiz.getRandomItem(pack.getCards(), AbstractDungeon.cardRandomRng));
        makeInHand(tar);
    }

    public void upp() {
        upgradeBlock(3);
    }
}