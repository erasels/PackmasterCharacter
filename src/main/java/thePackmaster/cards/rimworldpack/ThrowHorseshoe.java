package thePackmaster.cards.rimworldpack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.boardgamepack.BoardGameKeywordManager;
import thePackmaster.powers.rimworldpack.MoodPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ThrowHorseshoe extends AbstractPackmasterCard {
    public final static String ID = makeID(ThrowHorseshoe.class.getSimpleName());

    public ThrowHorseshoe() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage);
        addToBot(new DiscardAction(p, p, 1, true));
        addToBot(new ApplyPowerAction(p, p, new MoodPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}