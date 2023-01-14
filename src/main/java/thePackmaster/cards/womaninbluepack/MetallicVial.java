package thePackmaster.cards.womaninbluepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MetallicVial extends AbstractWomanInBlueCard {
    public final static String ID = makeID("MetallicVial");

    public static ArrayList<AbstractCard> choiceCards = new ArrayList<>();

    public MetallicVial() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

        exhaust = true;
    }


    public void action1() {
        AbstractPlayer p = Wiz.p();
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("PlatedArmorPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        Wiz.applyToSelf(new PlatedArmorPower(p, 4));

    }

    public void action2() {
        AbstractPlayer p = Wiz.p();
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("ThornsPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        Wiz.applyToSelf(new ThornsPower(p, 3));

    }

    public void action3() {
        AbstractPlayer p = Wiz.p();
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("ArtifactPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        Wiz.applyToSelf(new ArtifactPower(p, 2));
    }

    public void initializeChoiceCards() {
        String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoicePlatedArmor"),
                name,
                EXTENDED_DESCRIPTION[0], false, this::action1));

        choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoiceThorns"),
                name,
                EXTENDED_DESCRIPTION[1], false, this::action2));

        choiceCards.add(new AbstractWomanInBlueModalChoiceCard(SpireAnniversary5Mod.makeID("WIBChoiceArtifact"),
                name,
                EXTENDED_DESCRIPTION[2], false, this::action3));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (choiceCards.size() == 0) {
            initializeChoiceCards();
        }

        addToBot(new EasyModalChoiceAction(choiceCards, 1, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[0]));
    }


    public void upp() {
        setExhaustive2();
    }
}