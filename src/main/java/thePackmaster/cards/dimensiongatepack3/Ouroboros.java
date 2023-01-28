package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardInscryp;
import thePackmaster.powers.dimensiongate3pack.OuroborosPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Ouroboros extends AbstractDimensionalCardInscryp {
    public final static String ID = makeID("Ouroboros");

    public Ouroboros() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.POWER, AbstractCard.CardTarget.SELF);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new OuroborosPower(p,1));
    }

    public void upp() {
       isInnate = true;
    }
}