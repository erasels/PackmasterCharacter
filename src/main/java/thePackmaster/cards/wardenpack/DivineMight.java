package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.wardenpack.DivineMightPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.arcanapack.SunBeamEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DivineMight extends AbstractPackmasterCard {
    public final static String ID = makeID("DivineMight");

    private static final int DAMAGE = 25;
    private static final int DAMAGE_UPGRADE = 7;

    public DivineMight() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        AbstractDungeon.effectsQueue.add(new SunBeamEffect(m));
        CardCrawlGame.sound.playA("ATTACK_FLAME_BARRIER", 0.25F);
        Wiz.applyToSelf(new DivineMightPower(p, 1));
    }

    public void upp() {
        upgradeDamage(DAMAGE_UPGRADE);
    }
}
