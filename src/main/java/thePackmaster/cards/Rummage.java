package thePackmaster.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Rummage extends AbstractPackmasterCard {
    public final static String ID = makeID("Rummage");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Rummage() {
        super(ID, 2, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    int x = 0;
                    for (AbstractCard q : AbstractDungeon.player.hand.group) {
                        if (!q.freeToPlay())
                            if (q.costForTurn > x)
                                x = q.costForTurn;
                    }
                    ArrayList<AbstractCard> possCardsList = new ArrayList<>();
                    for (AbstractCard q : AbstractDungeon.player.hand.group) {
                        if (!q.freeToPlay())
                            if (q.costForTurn == x)
                                possCardsList.add(q);
                    }
                    if (!possCardsList.isEmpty()) {
                        flash();
                        AbstractCard q = possCardsList.get(AbstractDungeon.cardRandomRng.random(possCardsList.size() - 1));
                        q.setCostForTurn(q.costForTurn - 1);
                        q.isCostModifiedForTurn = true;
                        q.superFlash();
                    }
                }
            });
        }

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}