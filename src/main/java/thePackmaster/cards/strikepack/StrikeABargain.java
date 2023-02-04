package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.strikepack.StrikeABargainPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeABargain extends AbstractStrikePackCard {
    public final static String ID = makeID("StrikeABargain");

    public StrikeABargain() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 6;
        exhaust = true;

        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.SLASH_VERTICAL);

        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                for (AbstractCard c:p.hand.group
                ) {
                    if (c.type == CardType.ATTACK){
                        if (c.cost > 0) {
                            c.freeToPlayOnce = true;
                        }
                    }
                }
            }
        });

    }

    public void upp() {
        exhaust = false;
        this.upgradeDamage(3);
    }
}