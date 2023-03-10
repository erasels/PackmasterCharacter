package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class LuckyHorseshoe extends AbstractPackmasterCard {
    public final static String ID = makeID("LuckyHorseshoe");
    // intellij stuff attack, all_enemy, uncommon, 8, 3, , , , 

    public LuckyHorseshoe() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 8;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!AbstractDungeon.player.drawPile.isEmpty()) {
                    AbstractCard target = AbstractDungeon.player.drawPile.getRandomCard(AbstractDungeon.cardRandomRng);
                    target.setCostForTurn(0);
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(3);
    }
}