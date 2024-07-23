package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Overload extends AbstractEchoCard {

    public final static String ID = makeID(Overload.class.getSimpleName());

    private static final int COST = 1;

    public Overload(){
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        baseDamage = 10;
        magicNumber = baseMagicNumber = 20;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
        atb(new ModifyDamageAction(this.uuid, this.magicNumber));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : GetAllInBattleInstances.get(Overload.this.uuid))
                    c.cost += 1;

                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upMagic(10);
    }
}
