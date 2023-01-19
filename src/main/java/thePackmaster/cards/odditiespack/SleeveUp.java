package thePackmaster.cards.odditiespack;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.odditiespack.PackmasterFoilPatches;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SleeveUp extends AbstractOdditiesCard implements SpawnModificationCard {
    public final static String ID = makeID("SleeveUp");
    // intellij stuff skill, self, uncommon, , , , , 3, 1

    public SleeveUp() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void onRewardListCreated(ArrayList<AbstractCard> rewardCards) {
        for (AbstractCard c : rewardCards) {
            PackmasterFoilPatches.makeFoil(c);
            CardModifierManager.addModifier(c, new RetainMod());
            c.superFlash(Color.SKY.cpy());
        }
    }
}