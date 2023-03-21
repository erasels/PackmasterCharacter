package thePackmaster.cards.colorlesspack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.colorlesspack.IsGhostModifier;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.att;

public class Ghost extends AbstractColorlessPackCard implements StartupCard {
    public final static String ID = makeID("Ghost");
    // intellij stuff skill, enemy, uncommon, , , , , 12, 4

    public Ghost() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new IgnitePower(m, magicNumber));
    }

    @Override
    public boolean atBattleStartPreDraw() {
        att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int index = AbstractDungeon.player.drawPile.group.indexOf(Ghost.this);
                AbstractDungeon.player.drawPile.removeCard(Ghost.this);
                ArrayList<AbstractCard> possibilities = new ArrayList<>();
                AbstractDungeon.player.drawPile.group.forEach(q -> {
                    if (!q.cardID.equals(Ghost.ID) && !q.cardID.equals(ThePrism.ID)) { // Sometime hook this later than other startups; not a bug or anything but it'll prevent easy guesses
                        possibilities.add(q);
                    }
                });
                AbstractCard disguise = Wiz.getRandomItem(possibilities, AbstractDungeon.cardRandomRng).makeStatEquivalentCopy();
                CardModifierManager.addModifier(disguise, new IsGhostModifier(Ghost.this));
                AbstractDungeon.player.drawPile.group.add(index, disguise);
            }
        });
        return true;
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}