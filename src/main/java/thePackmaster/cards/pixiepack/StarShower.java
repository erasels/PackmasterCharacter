package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.pixiepack.StarShowerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StarShower extends AbstractPixieCard {
    public final static String ID = makeID("StarShower");

    private static final int baseAtk = 5;
    private static final int upgradeAtk = 7;

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

    @Override //zhs card text thing
    public void initializeDescriptionCN() {
        super.initializeDescriptionCN();
        if (Settings.language == Settings.GameLanguage.ZHS && this.description.size() != 0 ) {
                this.description.get(1).text = this.description.get(1).text + "，";
                this.description.remove(2);
        }
    }

}
