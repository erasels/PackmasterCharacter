package thePackmaster.cards.colorlesspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.colorlesspack.PrismAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class PrismShard extends AbstractColorlessPackCard {
    public final static String ID = makeID("PrismShard");
    // intellij stuff attack, enemy, special, 8, 1, , , , 

    public PrismShard() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        baseDamage = 8;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PrismAction(this));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (AbstractDungeon.player.exhaustPile.group.stream().filter(q -> q.cardID.equals(PrismShard.ID)).count() >= 2) {
                    AbstractCard q = new ThePrism();
                    if (upgraded) q.upgrade();
                    att(new MakeTempCardInHandAction(q));
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(1);
    }
}