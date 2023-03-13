package thePackmaster.cards.secretlevelpack;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.makeInHand;

public class MysteryBox extends AbstractSecretLevelCard {
    public final static String ID = makeID("MysteryBox");
    // intellij stuff skill, enemy, common, , , , , 2, 1

    public MysteryBox() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();
        if (AbstractDungeon.getCurrMapNode().getRoomSymbol(true).equals("?")) {
            c.setCostForTurn(0);
        }
        makeInHand(c);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.getCurrMapNode().getRoomSymbol(true).equals("?") ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}