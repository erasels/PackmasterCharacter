package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import java.util.HashSet;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.att;

public class RippedPecs extends AbstractPackmasterCard {
    public final static String ID = makeID("RippedPecs");

    private boolean synergyOn;
    public RippedPecs() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        synergyOn = (hasSynergy());

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (synergyOn) {
                    Wiz.applyToSelfTop(new StrengthPower(p, magicNumber));
                    att(new VFXAction(p, new InflameEffect(p), 0.25F));
                    att(new VFXAction(p, new InflameEffect(p), 0.25F));
                    att(new VFXAction(p, new InflameEffect(p), 0.25F));
                    att(new SFXAction("MONSTER_CHAMP_CHARGE"));
                } else {
                    Wiz.applyToSelfTop(new StrengthPower(p, 1));
                }
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        if (hasSynergy()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}