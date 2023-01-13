package thePackmaster.cards.clawpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CloakAndClaw extends AbstractClawCard {
    public final static String ID = makeID("CloakAndClaw");

    public CloakAndClaw() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new GhostClaw();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard c = new GhostClaw();
            Wiz.atb(new MakeTempCardInHandAction(c));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}