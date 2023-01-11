package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.pixiepack.FastHandsPower;
import thePackmaster.powers.pixiepack.UpgradedFastHandsPower;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FastHands extends AbstractPackmasterCard {
    public final static String ID = makeID("FastHands");

    public FastHands() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW);

        setBackgroundTexture("anniv5Resources/images/512/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp(){}

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (!upgraded) addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new FastHandsPower(abstractPlayer, 1)));
        else addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new UpgradedFastHandsPower(abstractPlayer, 1)));
    }
}
