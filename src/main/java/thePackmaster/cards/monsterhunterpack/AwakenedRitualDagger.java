package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import thePackmaster.actions.monsterhunterpack.AwakenedDaggerAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AwakenedRitualDagger extends AbstractMonsterHunterCard {
    public final static String ID = makeID("AwakenedRitualDagger");

    public static final int DAMAGE = 45;
    public static final int UPG_DAMAGE = 10;

    public AwakenedRitualDagger() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        this.exhaust = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new HemokinesisEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY)));
        addToBot(new WaitAction(0.4f));
        addToBot(new AwakenedDaggerAction(m, new DamageInfo(m, damage, DamageInfo.DamageType.NORMAL)));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}