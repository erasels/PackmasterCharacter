package thePackmaster.cards.intothebreachpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.intothebreachpack.ReboundVolleyAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class ReboundVolley extends IntoTheBreachCard implements BranchingUpgradesCard {
    public final static String ID = makeID("ReboundVolley");

    public ReboundVolley() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 7;
        secondDamage = baseSecondDamage = 14;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // This code used to move the card out of the way of the animation
        ReboundVolley thisCard = this;
        int destinationX = Settings.WIDTH / 3;
        int destinationY = Settings.HEIGHT / 3;
        att(new AbstractGameAction() {
            @Override
            public void update() {
                if (destinationX != -1.0f) {
                    thisCard.target_x = destinationX;
                }
                if (destinationY != -1.0f) {
                    thisCard.target_y = destinationY;
                }
                this.isDone = true;
            }
        });

        // This code is what actually does the card stuff lol
        atb(new ReboundVolleyAction(this, m, magicNumber));
    }

    @Override
    public void upp() {
        if (isBranchUpgrade()) branchUpgrade();
        else baseUpgrade();
    }

    public void baseUpgrade() {
        upgradeDamage(2);
        upgradeSecondDamage(4);
    }

    public void branchUpgrade() {
        upgradeDamage(-1);
        upgradeSecondDamage(-2);
        upgradeMagicNumber(1);
        rawDescription = CardCrawlGame.languagePack.getCardStrings(ReboundVolley.ID).EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    // This method is used so the second damage value on the card
    // (the "to a random other enemy" one)
    // doesn't change based on the targeted enemy's powers
    // (e.g. Vulnerable)
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.applyPowers();
        int tmp = baseSecondDamage;
        baseSecondDamage = -1;
        super.calculateCardDamage(mo);
        baseSecondDamage = tmp;
    }

    public void superCalculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }
}
