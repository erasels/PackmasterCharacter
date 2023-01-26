package thePackmaster.cards.womaninbluepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class QuickBrew extends AbstractWomanInBlueCard {
    public final static String ID = makeID("QuickBrew");

    public static ArrayList<AbstractCard> choiceCards = new ArrayList<>();

    public QuickBrew() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }


    public void action1() {
        AbstractPlayer p = Wiz.p();
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("StrengthPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        Wiz.applyToSelf(new StrengthPower(p, magicNumber));

    }

    public void action2() {
        AbstractPlayer p = Wiz.p();
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("DexterityPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        Wiz.applyToSelf(new DexterityPower(p, magicNumber));

    }

    public void action3() {
        AbstractPlayer p = Wiz.p();
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("FocusPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        Wiz.applyToSelf(new FocusPower(p, magicNumber));

    }

    public void initializeChoiceCards() {
        choiceCards.clear();
        String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoiceStrength"),
                name,
                EXTENDED_DESCRIPTION[0], false, this::action1));

        if (this.upgraded) {
            choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoiceDexterity"),
                    name,
                    EXTENDED_DESCRIPTION[1], false, this::action2));
        }

        choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoiceFocus"),
                name,
                EXTENDED_DESCRIPTION[2], false, this::action3));

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        initializeChoiceCards();

        for (AbstractCard c : choiceCards) {
            c.baseMagicNumber = c.magicNumber = magicNumber;
        }

        addToBot(new EasyModalChoiceAction(choiceCards, 1, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[0]));
    }


    public void upp() {
    }
}