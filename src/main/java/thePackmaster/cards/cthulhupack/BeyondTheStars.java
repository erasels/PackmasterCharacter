package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.entropypack.EntropyPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BeyondTheStars extends AbstractCthulhuCard {
    public final static String ID = makeID("BeyondTheStars");

    public BeyondTheStars() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL);
        baseDamage = 4;
        isMultiDamage = true;
        cardsToPreview = new Madness();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        AbstractCard c = new Madness();
        if (upgraded) c.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c.makeCopy(), 1));
    }

    public void upp() {
        cardsToPreview.upgrade();
    }
}