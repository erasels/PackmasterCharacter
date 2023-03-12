package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Hailstorm extends BitingColdCard {
    // TODO: add art lmao
    public final static String ID = makeID("Hailstorm");

    public Hailstorm() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        damage = baseDamage = 6;
        magicNumber = baseMagicNumber = 2;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new BlizzardEffect(magicNumber, AbstractDungeon.getMonsters().shouldFlipVfx()), Settings.FAST_MODE ? 0.25F : 1.0F);
        for (int i = 0; i < magicNumber; i++) {
            allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
        magicNumber = baseMagicNumber += 1;
        applyPowers();
    }

    public void upp() {
        upgradeDamage(2);
    }
}