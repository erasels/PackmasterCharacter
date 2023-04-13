package thePackmaster.cards.womaninbluepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ChaosVial extends AbstractWomanInBlueCard {
    public final static String ID = makeID("ChaosVial");

    public static ArrayList<AbstractCard> choiceCards = new ArrayList<>();

    public ChaosVial() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("ChaosPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));

        for (int i = 0; i < 3; ++i) {
            this.addToBot(new AbstractGameAction() {
                public void update() {
                    this.addToBot(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
                    this.isDone = true;
                }
            });
        }
    }


    public void upp() {
        upgradeBaseCost(2);
    }
}