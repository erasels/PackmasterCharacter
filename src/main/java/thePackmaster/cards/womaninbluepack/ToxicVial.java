package thePackmaster.cards.womaninbluepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ToxicVial extends AbstractWomanInBlueCard {
    public final static String ID = makeID("ToxicVial");

    public static ArrayList<AbstractCard> choiceCards = new ArrayList<>();

    private static AbstractMonster targetMon;

    public ToxicVial() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);

        exhaust = true;
    }


    public void action1() {
        AbstractPlayer p = Wiz.p();
        AbstractMonster m = targetMon;
        if (m != null) {
            addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("FearPotion.png"), p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, 3F, 0.6F, false, false), 0.6F));
            Wiz.applyToEnemy(m, new VulnerablePower(m, 3, false));
            targetMon = null;
        }

    }

    public void action2() {
        AbstractPlayer p = Wiz.p();
        AbstractMonster m = targetMon;
        if (m != null) {
            addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("WeakPotion.png"), p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, 3F, 0.6F, false, false), 0.6F));
            Wiz.applyToEnemy(m, new WeakPower(m, 3, false));
            targetMon = null;
        }

    }

    public void action3() {
        AbstractPlayer p = Wiz.p();
        AbstractMonster m = targetMon;
        if (m != null) {
            addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("PoisonPotion.png"), p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, 3F, 0.6F, false, false), 0.6F));
            Wiz.applyToEnemy(m, new PoisonPower(m, p, 8));
            targetMon = null;
        }


    }

    public void initializeChoiceCards() {
        choiceCards.clear();
        String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;


        if (this.upgraded) {
            choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoiceVulnerable"),
                    name,
                    EXTENDED_DESCRIPTION[0], false, this::action1));
        }

        choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoiceWeak"),
                name,
                EXTENDED_DESCRIPTION[1], false, this::action2));

        choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoicePoison"),
                name,
                EXTENDED_DESCRIPTION[2], false, this::action3));


    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        targetMon = m;
        initializeChoiceCards();

        addToBot(new EasyModalChoiceAction(choiceCards, 1, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[0]));

    }


    public void upp() {
    }
}