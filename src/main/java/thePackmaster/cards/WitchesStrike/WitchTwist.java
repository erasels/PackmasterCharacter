package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Leap;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.witchesstrikepack.InscribedMod;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.ISCARDMODIFIED;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WitchTwist extends AbstractWitchStrikeCard {
    public final static String ID = makeID("WitchTwist");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public WitchTwist() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
        CardModifierManager.addModifier(this,new InscribedMod(true,true));

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber ; i++) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.player.hand.isEmpty()) {
                        ArrayList<AbstractCard> Uninscribed = new ArrayList<>();
                        for (AbstractCard c : AbstractDungeon.player.hand.group) {
                            if (!c.hasTag(ISCARDMODIFIED)) {
                                Uninscribed.add(c);
                            }
                        }
                        if (!Uninscribed.isEmpty()) {
                            AbstractCard targetCard = Uninscribed.get(AbstractDungeon.cardRandomRng.random(Uninscribed.size() - 1));
                            CardModifierManager.addModifier(targetCard, new InscribedMod(false,true));
                            targetCard.superFlash(Color.VIOLET.cpy());
                        }
                    }
                    isDone = true;
                }
            });
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return Leap.ID;
    }
}

