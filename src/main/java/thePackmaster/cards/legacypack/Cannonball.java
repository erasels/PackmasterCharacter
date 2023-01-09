package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.legacypack.ShootAnythingAction;

import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cannonball extends AbstractLegacyCard {
    public final static String ID = makeID("Cannonball");

    private static final int POWER = 6;
    private static final int UPGRADE_BONUS = 2;

    public Cannonball() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        this.baseDamage = POWER;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ShootAnythingAction(m, TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("vfx/Cannonball.png")), false));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public void upp() {
        upgradeDamage(UPGRADE_BONUS);
    }
}