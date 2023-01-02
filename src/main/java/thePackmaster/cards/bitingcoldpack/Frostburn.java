package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class Frostburn extends AbstractPackmasterCard {
    public final static String ID = makeID("Frostburn");

    public Frostburn() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        magicNumber = baseMagicNumber = 12;
        secondMagic = baseSecondMagic = 2;
        this.cardsToPreview = new Burn();
        this.cardsToPreview.upgrade();

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new ScreenOnFireEffect(), 0.5F));

        addToBot(new WaitAction(0.4F));

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters)
            applyToEnemy(mo, new FrostbitePower(mo, magicNumber));

        addToBot(new WaitAction(0.6F));
        addToBot(new MakeTempCardInDiscardAction(this.cardsToPreview, 2));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}