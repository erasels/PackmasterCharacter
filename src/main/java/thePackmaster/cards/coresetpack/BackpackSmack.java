package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.highenergypack.Food;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class BackpackSmack extends AbstractPackmasterCard {
    public final static String ID = makeID("BackpackSmack");

    private boolean synergyOn;

    public BackpackSmack() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        magicNumber = baseMagicNumber = 2;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        synergyOn = (hasSynergy());

        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                dmgTop(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            }
            return true;
        }));

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (synergyOn) {
                    Wiz.atb(new GainEnergyAction(1));
                }
            }
        });

    }
    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (hasSynergy()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}