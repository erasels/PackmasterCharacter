package thePackmaster.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

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
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ArrayList<AbstractCard> possCardsList = new ArrayList<>();
                int highestcost = 0;

                for (int i = 0; i < magicNumber; i++) {
                    for (AbstractCard q : AbstractDungeon.player.hand.group) {
                        if (q.cost >= 0 && q.costForTurn > 0) {
                            if (Wiz.getLogicalCardCost(q) == highestcost) {
                                possCardsList.add(q);
                            } else if (Wiz.getLogicalCardCost(q) > highestcost) {
                                highestcost = Wiz.getLogicalCardCost(q);
                                possCardsList.clear();
                                possCardsList.add(q);
                            }
                        }
                    }

                    if (!possCardsList.isEmpty()) {
                        AbstractCard q = possCardsList.get(AbstractDungeon.cardRandomRng.random(possCardsList.size() - 1));
                        possCardsList.clear();
                        highestcost = 0;
                        q.setCostForTurn(q.costForTurn - 1);
                        q.isCostModifiedForTurn = true;
                        q.superFlash();
                    }
                }
            }
        });
    }


    public void upp() {
        upgradeMagicNumber(1);
    }
}