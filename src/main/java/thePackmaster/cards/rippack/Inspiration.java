package thePackmaster.cards.rippack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.cardmodifiers.rippack.RippableModifier;
import thePackmaster.vfx.rippack.InspirationEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.isArtCard;

public class Inspiration extends AbstractRipCard {
    public final static String ID = makeID("Inspiration");


    public Inspiration() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        CardModifierManager.addModifier(this, new RippableModifier());
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int statusAndArtCardsInExhaust = (int) AbstractDungeon.player.exhaustPile.group.stream().filter(card -> isArtCard(card) || card.type == CardType.STATUS).count();
        rawDescription = upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + statusAndArtCardsInExhaust;
        if (statusAndArtCardsInExhaust == 1) {
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    @Override
    public void upp() {
        exhaust = false;
        uDesc();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int statusAndArtCardsInExhaust = (int) AbstractDungeon.player.exhaustPile.group.stream().filter(card -> isArtCard(card) || card.type == CardType.STATUS).count();

        AbstractGameEffect off = InspirationEffect.Off();
        atb(new VFXAction(off));
        if(statusAndArtCardsInExhaust > 0) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    if (off.isDone) {
                        isDone = true;
                    }
                }
            });
            atb(new VFXAction(InspirationEffect.On()));
            atb(new DrawCardAction(statusAndArtCardsInExhaust));
        }
    }
}