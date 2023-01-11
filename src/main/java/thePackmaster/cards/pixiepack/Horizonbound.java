package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
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
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.PixiePack;
import thePackmaster.powers.pixiepack.FastHandsPower;
import thePackmaster.powers.pixiepack.HorizonboundPower;
import thePackmaster.powers.pixiepack.UpgradedFastHandsPower;

import javax.swing.text.html.HTMLDocument;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Horizonbound extends AbstractPixieCard {
    public final static String ID = makeID("Horizonbound");

    public Horizonbound() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
    }

    @Override
    public void upp()
    {
        this.isEthereal=false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
    {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new HorizonboundPower(abstractPlayer, 0)));
    }
}
