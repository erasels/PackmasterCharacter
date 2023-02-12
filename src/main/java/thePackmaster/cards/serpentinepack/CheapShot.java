package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CheapShot extends AbstractSerpentineCard {

    private static final int COST = 1;
    private static final int MAGIC = 4;
    private static final int DAMAGE = 4;
    public final static String ID = makeID("CheapShot");


    public CheapShot() {
        super(ID, COST, AbstractCard.CardType.ATTACK, CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new VulnerablePower(abstractMonster, magicNumber, false), magicNumber));
    }

    @Override
    public void upp() {
        isInnate = true;
    }
}
