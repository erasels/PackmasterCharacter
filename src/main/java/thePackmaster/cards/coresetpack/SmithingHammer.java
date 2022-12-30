package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SmithingHammer extends AbstractPackmasterCard {
    public final static String ID = makeID("SmithingHammer");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public SmithingHammer() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        //TODO - Smithing visual effect sync'ed to the 3 hits
        for (int i = 0; i < magicNumber; i++) {
            this.addToBot(new ArmamentsAction(this.upgraded));
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}