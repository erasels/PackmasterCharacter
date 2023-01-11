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
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.pixiepack.EnchantmentAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.PixiePack;

import javax.swing.text.html.HTMLDocument;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NeutronStar extends AbstractPackmasterCard {
    public final static String ID = makeID("NeutronStar");

    private static final int baseMgk = 1;
    private static final int upgradeMgk = 2;

    public NeutronStar() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW);
        this.baseMagicNumber = this.magicNumber = baseMgk;
        this.tags.add(PixiePack.pixieTags.ENCHANTMENT);

        setBackgroundTexture("anniv5Resources/images/512/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(upgradeMgk-baseMgk);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,magicNumber)));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new LoseStrengthPower(abstractPlayer,magicNumber)));
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (AbstractDungeon.player.hand.contains(this) && PixiePack.isForeign(c) && c.type==CardType.ATTACK)
        {
            flash();
            AbstractCard toPlay = makeStatEquivalentCopy();
            addToBot(new EnchantmentAction(toPlay, m));
            AbstractDungeon.effectList.add(new LightningEffect(current_x,current_y));
        }
        super.onPlayCard(c, m);
    }
}
