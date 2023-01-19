package thePackmaster.cards.womaninbluepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DynamicVial extends AbstractWomanInBlueCard {
    public final static String ID = makeID("DynamicVial");

    public static ArrayList<AbstractCard> choiceCards = new ArrayList<>();

    public DynamicVial() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        exhaust = true;
    }


    public void action1() {
        AbstractPlayer p = Wiz.p();
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("EnergyPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        addToBot(new GainEnergyAction(2));

    }

    public void action2() {
        AbstractPlayer p = Wiz.p();
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("DrawPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        addToBot(new DrawCardAction(3));

    }

    public void action3() {

    }

    public void initializeChoiceCards() {
        String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoiceEnergy"),
                name,
                EXTENDED_DESCRIPTION[0], false, this::action1));

        choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoiceDraw"),
                name,
                EXTENDED_DESCRIPTION[1], false, this::action2));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (choiceCards.size() == 0) {
            initializeChoiceCards();
        }

        addToBot(new EasyModalChoiceAction(choiceCards, 1, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[0]));
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }


    public void upp() {
        upgradeBaseCost(0);
    }
}