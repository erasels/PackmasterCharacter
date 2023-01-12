package thePackmaster.cards.batterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OpenBracket extends AbstractPackmasterCard {
    public final static String ID = makeID("OpenBracket");

    public OpenBracket() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = 2;
        magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (p.drawPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                // Add attacks to group.
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                for(AbstractCard card : p.drawPile.group)
                {
                    if (card.type == CardType.ATTACK)
                    tmp.addToRandomSpot(card);
                }

                if (tmp.size() == 0) {
                    this.isDone = true;
                    return;
                }

                AbstractCard caard;

                // Put attacks from group on top and draw.
                for(int i = 0; i < OpenBracket.this.magicNumber; ++i) {
                    if (!tmp.isEmpty()) {
                        tmp.shuffle();
                        caard = tmp.getBottomCard();
                        tmp.removeCard(caard);
                        if (p.hand.size() == 10) {
                            p.createHandIsFullDialog();
                        } else {
                            if (!AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID))
                            {
                                p.drawPile.group.remove(caard);
                                p.drawPile.addToTop(caard);
                                this.addToTop(new DrawCardAction(1));
                            }
                        }
                    }
                }

                this.isDone = true;
            }
        });
        this.addToBot(new ApplyPowerAction(p, p, new NoDrawPower(p)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
