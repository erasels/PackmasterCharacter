package thePackmaster.cards.colorlesspack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.colorlesspack.LunchBoxPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class LunchBox extends AbstractColorlessPackCard implements OnObtainCard {
    public final static String ID = makeID("LunchBox");
    // intellij stuff skill, self, uncommon, , , 6, 3, , 

    public LunchBox() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new LunchBoxPower());
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.player.heal(3);
    }

    public void upp() {
        upgradeBlock(3);
    }
}