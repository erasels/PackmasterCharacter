package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.pixiepack.DimensionBreachAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.PixiePack;

import javax.swing.text.html.HTMLDocument;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DimensionBreach extends AbstractPackmasterCard {
    public final static String ID = makeID("DimensionBreach");

    private static final int baseMgk = 3;
    private static final int upgradeMgk = 5;

    public DimensionBreach() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, ThePackmaster.Enums.PACKMASTER_RAINBOW);
        this.baseMagicNumber = this.magicNumber = baseMgk;

        setBackgroundTexture("anniv5Resources/images/512/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(upgradeMgk-baseMgk);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for(int i = 0; i < this.magicNumber; i++)
        {
            addToBot(new DimensionBreachAction(abstractMonster));
        }
    }
}
