package thePackmaster.cards.odditiespack;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EightBall extends AbstractPackmasterCard {
    public final static String ID = makeID("EightBall");
    // intellij stuff attack, enemy, special, 12, 3, 10, 2, , 

    public EightBall() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 21;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    protected Texture getPortraitImage() {
        if (upgraded) {
            return ImageMaster.loadImage("anniv5Resources/images/cards/NineBall_p.png");
        }
        return super.getPortraitImage();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            ++timesUpgraded;
            upgraded = true;
            name = cardStrings.EXTENDED_DESCRIPTION[0];
            initializeTitle();
            upp();
            loadCardImage("anniv5Resources/images/cards/NineBall.png");
        }
    }

    public void upp() {
        upgradeDamage(6);
    }
}