package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FlailingTendril extends AbstractCthulhuCard {
    public final static String ID = makeID("FlailingTendril");

    private static final int ATTACK_DMG = 10;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 3;

    public FlailingTendril() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = ATTACK_DMG;
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Lunacy();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster m2 = Wiz.getRandomEnemy();
        dmg(m2, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (freeToPlay() || costForTurn == 0 || cost == 0) {
            m2 = Wiz.getRandomEnemy();
            dmg(m2, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
        loseSanity(magicNumber);
    }


    public void triggerOnGlowCheck() {
        this.glowColor = AbstractPackmasterCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (freeToPlay() || costForTurn == 0 || cost == 0) {
            this.glowColor = AbstractPackmasterCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
    }
}