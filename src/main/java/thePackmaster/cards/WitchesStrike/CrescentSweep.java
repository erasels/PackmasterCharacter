package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.LegSweep;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.cardmodifiers.witchesstrikepack.InscribedMod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CrescentSweep extends AbstractWitchStrikeCard {
    public final static String ID = makeID("CrescentSweep");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CrescentSweep() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ManifestAction(new CrescentMoon()));
        if (upgraded){
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (AbstractDungeon.player.hand.size() > 0) {
                        ArrayList<AbstractCard> Uninscribed = new ArrayList<>();
                        for (AbstractCard c : AbstractDungeon.player.hand.group){
                            if (!CardModifierManager.hasModifier(c,"Inscribed")){
                                Uninscribed.add(c);
                            }
                        }
                        if (!Uninscribed.isEmpty()) {
                            AbstractCard targetCard = Uninscribed.get(AbstractDungeon.cardRandomRng.random(Uninscribed.size() - 1));
                            CardModifierManager.addModifier(targetCard, new InscribedMod(false,true));
                        }
                    }
                    isDone = true;
                }
            });
        }
    }
    public void upp() {
        upgradeBlock(2);
    }
    @Override
    public String cardArtCopy() {
        return LegSweep.ID;
    }
}
