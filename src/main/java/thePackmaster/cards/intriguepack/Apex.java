package thePackmaster.cards.intriguepack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Apex extends AbstractIntrigueCard {
    public final static String ID = makeID("Apex");

    public Apex() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                boolean ascend = true;

                // Check if any card is not precious.
                for(AbstractCard c : Wiz.p().hand.group)
                {
                    if (!isPrecious(c))
                        ascend = false;
                }

                // Ascend if all cards in hand are precious.
                if (ascend)
                {
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            CardCrawlGame.sound.playA("ATTACK_FLAME_BARRIER", 0.25F);
                            CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
                            AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.YELLOW, true));
                            isDone = true;
                        }
                    });
                    this.addToBot(new VFXAction(p, new InflameEffect(p), 0.1F));

                    this.addToBot(new GainEnergyAction(2));
                    this.addToBot(new DrawCardAction(2));
                    this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 10), 10));
                    this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 10), 10));
                }

                // Demote all other cards.
                for(AbstractCard c : Wiz.p().hand.group)
                {
                        fullDemote(c);
                }

                // And this.
                fullDemote(Apex.this);

                isDone = true;
            }
        });
    }

    public void triggerOnGlowCheck() {
        // Check if any card is not precious.
        for(AbstractCard c : Wiz.p().hand.group)
        {
            if (!isPrecious(c)) {
                this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
                return;
            }
        }

        this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}
