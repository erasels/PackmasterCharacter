package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.packs.CthulhuPack;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GazeTheVoid extends AbstractCthulhuCard {
    public final static String ID = makeID("GazeTheVoid");

    private static final int ATTACK_DMG = 5;
    private static final int HITS = 3;

    public GazeTheVoid() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = HITS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
        for (int i = 0; i < CthulhuPack.lunacyThisCombat; i++) {
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + (CthulhuPack.lunacyThisCombat + 1) + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    public void upp() {
        upgradeDamage(3);
    }
}