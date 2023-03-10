package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class LuckyHorseshoe extends AbstractJockeyCard {
    public final static String ID = makeID("LuckyHorseshoe");
    // intellij stuff attack, all_enemy, uncommon, 8, 3, , , , 

    public LuckyHorseshoe() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster tar = AbstractDungeon.getRandomMonster();
                dmgTop(m, AttackEffect.NONE);
                att(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, tar.hb.cX, tar.hb.cY), 0.3F));
            }
        });
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!AbstractDungeon.player.drawPile.isEmpty()) {
                    ArrayList<AbstractCard> validCards = new ArrayList<>();
                    for (AbstractCard q : AbstractDungeon.player.drawPile.group) {
                        if (Wiz.getLogicalCardCost(q) > 0) {
                            validCards.add(q);
                        }
                    }
                    AbstractCard target = Wiz.getRandomItem(validCards, AbstractDungeon.cardRandomRng);
                    target.setCostForTurn(0);
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(3);
    }
}