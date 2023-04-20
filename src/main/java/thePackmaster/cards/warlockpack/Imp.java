package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.thornDmg;

public class Imp extends AbstractWarlockCard {
    public final static String ID = makeID(Imp.class.getSimpleName());

    private static final int COST = -2;
    private static final int DAMAGE = 3;
    private static final int DAMAGE_INCREASE = 1;
    private static final int UPGRADE_DAMAGE_INCREASE = 1;

    public static int ImpDamageBonus = 0;

    public Imp() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        magicNumber = baseMagicNumber = DAMAGE;
        secondMagic = baseSecondMagic = DAMAGE_INCREASE;
        exhaust = true;
        this.applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster mon = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if(mon != null)
            thornDmg(mon, magicNumber);
        atb(new DrawCardAction(1));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                ImpDamageBonus += secondMagic;

                List<AbstractCard> cards = new ArrayList<>();
                cards.addAll(AbstractDungeon.player.hand.group);
                cards.addAll(AbstractDungeon.player.drawPile.group);
                cards.addAll(AbstractDungeon.player.discardPile.group);
                cards.addAll(AbstractDungeon.player.limbo.group);
                cards.addAll(AbstractDungeon.player.exhaustPile.group);

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
        this.magicNumber = this.baseMagicNumber = DAMAGE + ImpDamageBonus;
        this.isMagicNumberModified = ImpDamageBonus > 0;
    }

    public void triggerWhenAddedToHand() {
        this.dontTriggerOnUseCard = true;
        this.applyPowers();
        this.addToBot(new NewQueueCardAction(this, true, false, true));
    }
}
