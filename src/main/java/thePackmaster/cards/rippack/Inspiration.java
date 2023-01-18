package thePackmaster.cards.rippack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.vfx.rippack.InspirationEffect;

import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Inspiration extends AbstractRippableCard {
    public final static String ID = makeID("Inspiration");

    public Inspiration() {
        this(null, null);
    }

    public Inspiration(AbstractRippedArtCard artCard, AbstractRippedTextCard textCard) {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 12;
        if (artCard == null && textCard == null) {
            setRippedCards(new InspirationArt(this), new InspirationText(this));
        } else if(artCard == null){
            setRippedCards(new InspirationArt(this), textCard);
        } else {
            setRippedCards(artCard, new InspirationText(this));
        }
        exhaust = true;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int artCardsInExhaust = AbstractDungeon.player.exhaustPile.group.stream().filter(card -> card instanceof AbstractRippedArtCard).collect(Collectors.toList()).size();
        rawDescription = upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + artCardsInExhaust;
        if (artCardsInExhaust == 1) {
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
        int statusAndArtCardsInExhaust = AbstractDungeon.player.exhaustPile.group.stream().filter(card -> card instanceof AbstractRippedArtCard || card.type == CardType.STATUS).collect(Collectors.toList()).size();

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
