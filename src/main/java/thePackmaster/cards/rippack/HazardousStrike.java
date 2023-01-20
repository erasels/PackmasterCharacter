package thePackmaster.cards.rippack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.actions.rippack.ExhaustRandomNonArtCardsAction;
import thePackmaster.vfx.rippack.HazardousStrikeEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class HazardousStrike extends AbstractRippableCard {
    public final static String ID = makeID("HazardousStrike");

    public HazardousStrike() {
        this(null, null);
    }

    public HazardousStrike(AbstractRippedArtCard artCard, AbstractRippedTextCard textCard) {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 18;
        baseMagicNumber = magicNumber = 2;
        tags.add(CardTags.STRIKE);
        if (artCard == null && textCard == null) {
            setRippedCards(new HazardousStrikeArt(this), new HazardousStrikeText(this));
        } else if(artCard == null){
            setRippedCards(new HazardousStrikeArt(this), textCard);
        } else {
            setRippedCards(artCard, new HazardousStrikeText(this));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(8);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameEffect hyyah = HazardousStrikeEffect.JumpSlash(m);
        atb(new VFXAction(hyyah));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (hyyah.isDone) {
                    isDone = true;
                }
            }
        });
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public void onRip() {
        super.onRip();
        att(new ExhaustRandomNonArtCardsAction(magicNumber)); //att to it runs before making the new text/art cards in hand
        att(new VFXAction(AbstractDungeon.player, HazardousStrikeEffect.CutCardsInHand(), 0.25f,true));
    }
}
