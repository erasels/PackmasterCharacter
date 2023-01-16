package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.pixiepack.DrawSpecificCardAction;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class SowTheSeeds extends AbstractFarmerCard {
    public final static String ID = makeID("SowTheSeeds");
    private boolean attack = false;
    private boolean skill = false;
    private boolean status = false;
    private boolean power = false;
    private boolean curse = false;
    private int count = 0;
    public SowTheSeeds() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Fertilizer();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new MakeTempCardInHandAction(new Fertilizer()));
        Iterator<AbstractCard> typeDetect = AbstractDungeon.player.drawPile.group.iterator();
        while (typeDetect.hasNext()) {
            AbstractCard i = typeDetect.next();
            if (i.type == CardType.ATTACK && !attack) {
                count += 1;
                att(new DrawSpecificCardAction(i));
                attack = true;
            } else if (i.type == CardType.SKILL && !skill) {
                count += 1;
                att(new DrawSpecificCardAction(i));
                skill = true;
            } else if (i.type == CardType.POWER && !power) {
                count += 1;
                att(new DrawSpecificCardAction(i));
                power = true;
            } else if (i.type == CardType.STATUS && !status) {
                count += 1;
                att(new DrawSpecificCardAction(i));
                status = true;
            } else if (i.type == CardType.CURSE && !curse) {
                count += 1;
                att(new DrawSpecificCardAction(i));
                curse = true;
            }
            if (count >= magicNumber) {
                break;
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
