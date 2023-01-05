package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.stances.cthulhupack.NightmareStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GazeTheVoid extends AbstractCthulhuCard {
    public final static String ID = makeID("GazeTheVoid");

    private static final int ATTACK_DMG = 4;
    private static final int HITS = 3;
    private static final int UPGRADE_PLUS_HITS = 1;

    public GazeTheVoid() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = HITS;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dmg(m, AbstractGameAction.AttackEffect.SMASH);
        }
        addToBot(new ChangeStanceAction(new NightmareStance()));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PLUS_HITS);
    }
}