package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.actions.pixiepack.EnchantmentAction;
import thePackmaster.packs.PixiePack;
import thePackmaster.powers.pixiepack.NeutronStarPower;
import thePackmaster.powers.pixiepack.StarShowerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StarShower extends AbstractPixieCard {
    public final static String ID = makeID("StarShower");

    private static final int baseAtk = 4;
    private static final int upgradeAtk = 6;

    public StarShower() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = this.damage = baseAtk;
    }

    @Override
    public void upp() {
        this.upgradeDamage(upgradeAtk - baseAtk);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToTop(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, damage), AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StarShowerPower(abstractPlayer, damage)));
    }
}
