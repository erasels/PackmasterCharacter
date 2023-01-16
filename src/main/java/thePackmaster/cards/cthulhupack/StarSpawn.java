package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Plasma;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StarSpawn extends AbstractCthulhuCard {
    public final static String ID = makeID("StarSpawn");

    private static final int ATTACK_DMG = 13;
    private static final int HEAL = 6;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 3;
    private static final int UPGRADE_HEAL = 3;

    public StarSpawn() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = HEAL;
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new HealAction(p, p, magicNumber));
        addToBot(new ChannelAction(new Plasma()));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
        upgradeMagicNumber(UPGRADE_HEAL);
    }
}