package thePackmaster.cards.warlockpack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.thornDmg;

public class Imp extends AbstractPackmasterCard {
    public final static String ID = makeID(Imp.class.getSimpleName());

    private static final int COST = 0;
    private static final int DAMAGE = 4;
    private static final int DAMAGE_INCREASE = 1;
    private static final int UPGRADE_DAMAGE_INCREASE = 1;

    public Imp() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        magicNumber = baseMagicNumber = DAMAGE;
        secondDamage = baseSecondMagic = DAMAGE_INCREASE;
        AutoplayField.autoplay.set(this, true);
        exhaust = true;
        this.applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        thornDmg(AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng), magicNumber);
        atb(new DrawCardAction(1));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                List<AbstractCard> cards = new ArrayList<>();
                cards.addAll(AbstractDungeon.player.hand.group);
                cards.addAll(AbstractDungeon.player.drawPile.group);
                cards.addAll(AbstractDungeon.player.discardPile.group);

                for (AbstractCard c : cards) {
                    if (c instanceof Imp) {
                        c.applyPowers();
                        c.initializeDescription();
                    }
                }

                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upSecondMagic(UPGRADE_DAMAGE_INCREASE);//Apotheosis interaction Pog ?
    }

    @Override
    public void applyPowers() {
        int impsThisCombat = (int)AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(c -> c instanceof Imp).count();
        this.magicNumber = this.baseMagicNumber = DAMAGE + impsThisCombat;
        this.isMagicNumberModified = impsThisCombat > 0;
    }
}
