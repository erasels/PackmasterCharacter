package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Leap;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.actions.witchesstrikepack.MysticFlourishAction;
import thePackmaster.cardmodifiers.witchesstrikepack.InscribedMod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.util.CardArtRoller;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WitchTwist extends AbstractPackmasterCard {
    public final static String ID = makeID("WitchTwist");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public WitchTwist() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
        secondMagic = baseSecondMagic =1;
        CardModifierManager.addModifier(this,new InscribedMod(true,true));

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber ; i++) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (AbstractDungeon.player.hand.size() > 0) {
                        ArrayList<AbstractCard> Uninscribed = new ArrayList<>();
                        for (AbstractCard c : AbstractDungeon.player.hand.group) {
                            if (!CardModifierManager.hasModifier(c, "Inscribed")) {
                                Uninscribed.add(c);
                            }
                        }
                        if (!Uninscribed.isEmpty()) {
                            AbstractCard targetCard = Uninscribed.get(AbstractDungeon.miscRng.random(Uninscribed.size() - 1));
                            CardModifierManager.addModifier(targetCard, new InscribedMod(false,true));
                        }
                    }
                    isDone = true;
                }
            });
        }
        addToBot(new ApplyPowerAction(p,p,new FocusPower(p,secondMagic)));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
    @Override
    public String cardArtCopy() {
        return Leap.ID;
    }
}

