package thePackmaster.cards.scrypluspack;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.green.Reflex;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.commons.lang3.StringUtils;
import thePackmaster.patches.scryplus.CardSeenScriedInterface;
import thePackmaster.patches.scryplus.OnBeingScriedInterface;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RedIncense extends AbstractScryPlusCard
        implements OnBeingScriedInterface,
        CardSeenScriedInterface{

    public final static String ID = makeID(RedIncense.class.getSimpleName());
    public RedIncense() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        setMN(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }


    public void triggerOnExhaust()
    {
        bruh();
    }

    public void triggerOnManualDiscard()
    {
        bruh();
    }

    public void onBeingScried()
    {
        bruh();
    }

    private void bruh()
    {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        cantUseMessage = CardCrawlGame.languagePack.getCardStrings(Reflex.ID).EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void onSeenScried() {
        scryFlash();
    }
}
