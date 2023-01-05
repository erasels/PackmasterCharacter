package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Plasma;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.stances.cthulhupack.NightmareStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StarSpawn extends AbstractCthulhuCard {
    public final static String ID = makeID("StarSpawn");

    private static final int ATTACK_DMG = 13;
    private static final int HITS = 2;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 5;

    public StarSpawn() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = HITS;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new IncreaseMaxOrbAction(1));
        addToBot(new ChannelAction(new Plasma()));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
    }
}