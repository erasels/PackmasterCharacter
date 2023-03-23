package thePackmaster.cards.colorlesspack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.PlayFromDiscardAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class Manifest extends AbstractColorlessPackCard {
    public final static String ID = makeID("Manifest");
    // intellij stuff attack, enemy, common, 18, 6, , , , 

    public Manifest() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 20;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new SelectCardsAction(AbstractDungeon.player.discardPile.group, cardStrings.EXTENDED_DESCRIPTION[0], abstractCard -> (abstractCard.costForTurn >= 0 && abstractCard.costForTurn <= 1) || abstractCard.freeToPlayOnce,
                (cards) -> {
                    for (AbstractCard q : cards) {
                        att(new PlayFromDiscardAction(q));
                    }
                }));
    }

    public void upp() {
        upgradeDamage(6);
    }
}