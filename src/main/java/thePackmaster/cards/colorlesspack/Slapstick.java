package thePackmaster.cards.colorlesspack;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.colorless.Blind;
import com.megacrit.cardcrawl.cards.colorless.DramaticEntrance;
import com.megacrit.cardcrawl.cards.colorless.Trip;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Arrays;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.getRandomItem;
import static thePackmaster.util.Wiz.shuffleIn;

public class Slapstick extends AbstractColorlessPackCard {
    public final static String ID = makeID("Slapstick");
    // intellij stuff attack, enemy, common, 9, 3, , , , 

    public Slapstick() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        MultiCardPreview.add(this, new DramaticEntrance(), new Trip(), new Blind());
    }

    private static final List<String> options = Arrays.asList(
            DramaticEntrance.ID,
            Trip.ID,
            Blind.ID
    );

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        shuffleIn(CardLibrary.getCopy(getRandomItem(options, AbstractDungeon.cardRandomRng)));

    }

    public void upp() {
        upgradeDamage(3);
    }
}