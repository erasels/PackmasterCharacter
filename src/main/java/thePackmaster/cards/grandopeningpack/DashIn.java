package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.WhirlwindAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DashIn extends AbstractGrandOpeningCard {
    public final static String ID = makeID("DashIn");

    public DashIn() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.isInnate = true;
        this.baseDamage = this.damage = 4;
        this.baseBlock = this.block = 4;
        this.baseMagicNumber = this.magicNumber = 0;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        addToBot(new WhirlwindAction(abstractPlayer, this.multiDamage, DamageInfo.DamageType.NORMAL, this.freeToPlayOnce, this.energyOnUse+this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}