package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BackpackSmack extends AbstractPackmasterCard {
    public final static String ID = makeID("BackpackSmack");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public BackpackSmack() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (p.drawPile.size() > 9) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}