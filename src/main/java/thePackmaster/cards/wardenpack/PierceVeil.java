package thePackmaster.cards.wardenpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.commons.lang3.math.NumberUtils;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PierceVeil extends AbstractWardenCard {
    public final static String ID = makeID("PierceVeil");

    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 3;

    public PierceVeil() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> grp;
                if(!Wiz.p().drawPile.isEmpty()) {
                    int last = Wiz.p().drawPile.size();
                    grp = new ArrayList<>(Wiz.p().drawPile.group.subList(NumberUtils.max(0, (last-1) - magicNumber), last));
                    Wiz.att(new SelectCardsAction(grp, CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction").TEXT[0], cards -> {
                        for (AbstractCard c : cards) {
                            Wiz.p().drawPile.removeCard(c);
                            Wiz.hand().addToHand(c);
                        }
                    }));
                }
                isDone = true;
            }
        });
    }

    public void upp() {
            upgradeDamage(DAMAGE_UPGRADE);
            upgradeMagicNumber(1);
    }
}
