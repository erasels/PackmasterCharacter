package thePackmaster.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.odditiespack.PackmasterFoilPatches;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Vexed extends AbstractPackmasterCard {
    public final static String ID = makeID("Vexed");
    private static final int COST = -2;

    public Vexed() {
        super(ID, COST, CardType.CURSE, CardRarity.SPECIAL, CardTarget.NONE, CardColor.CURSE);
        SoulboundField.soulbound.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upp() {
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (PackmasterFoilPatches.isFoil(c)) {
            this.superFlash();
            this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
    }
}