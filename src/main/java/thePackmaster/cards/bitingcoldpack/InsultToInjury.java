package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class InsultToInjury extends BitingColdCard {
    public final static String ID = makeID("InsultToInjury");

    public InsultToInjury() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 5;
    }

    public void triggerOnGlowCheck() {
        boolean debuffCheck = false;
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            for (AbstractPower p : m.powers) {
                if (p.type == AbstractPower.PowerType.DEBUFF) {
                    this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                    debuffCheck = true;
                    break;
                }
            }
            if (debuffCheck)
                break;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 1;
        if (m.powers.size() > 0)
            for (AbstractPower power : m.powers)
                if (power.type == AbstractPower.PowerType.DEBUFF)
                    count++;
        for (int i = 0; i < count; i++)
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    public void upp() {
        upgradeDamage(1);
    }
}