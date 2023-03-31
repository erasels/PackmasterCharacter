package thePackmaster.cards.colorlesspack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAndDeckAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.colorlesspack.PrismAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class ThePrism extends AbstractColorlessPackCard implements StartupCard {
    public final static String ID = makeID("ThePrism");
    private boolean showPreview;
    // intellij stuff attack, all_enemy, rare, 8, 1, , , 8, 1

    public ThePrism(boolean showPreview) {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.showPreview = showPreview;
        baseDamage = 8;
        baseMagicNumber = magicNumber = 8;
        if (showPreview) cardsToPreview = new PrismShard(false);
    }

    public ThePrism() {
        this(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            atb(new PrismAction(this));
        }
    }

    @Override
    public boolean atBattleStartPreDraw() {
        att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.drawPile.removeCard(ThePrism.this);
                AbstractCard q = new PrismShard();
                if (upgraded) q.upgrade();
                att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        AbstractDungeon.player.exhaustPile.addToBottom(q.makeStatEquivalentCopy());
                    }
                });
                att(new MakeTempCardInDiscardAndDeckAction(q));
                att(new MakeTempCardInHandAction(q));
            }
        });

        return true;
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
        if (showPreview) {
            AbstractCard q = new PrismShard(false);
            q.upgrade();
            cardsToPreview = q;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ThePrism(showPreview);
    }
}