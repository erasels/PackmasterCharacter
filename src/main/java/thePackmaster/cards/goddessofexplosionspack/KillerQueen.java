package thePackmaster.cards.goddessofexplosionspack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.cardmodifiers.goddessofexplosionspack.DamageAllEnemiesModifier;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class KillerQueen extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("KillerQueen");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("HandpickUI"));

    private static final int MAGIC = 8;
    private static final int MAGIC_UP = 4;

    public KillerQueen() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new HandSelectAction(1, (c) -> true,
                list -> {
                    for (AbstractCard c : list) {
                        if (CardModifierManager.hasModifier(c,DamageAllEnemiesModifier.MOD_ID))
                            ((DamageAllEnemiesModifier)CardModifierManager.getModifiers(c,DamageAllEnemiesModifier.MOD_ID).get(0)).value += magicNumber;
                        else
                            CardModifierManager.addModifier(c, new DamageAllEnemiesModifier(magicNumber));

                        c.superFlash(Color.GOLD.cpy());
                    }
                },null, uiStrings.TEXT[1],true,false,false)
        );
    }

    @Override
    public void upp() {
        upgradeMagicNumber(MAGIC_UP);
    }
}
