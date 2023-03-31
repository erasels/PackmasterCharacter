package thePackmaster.cards.colorlesspack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import thePackmaster.cardmodifiers.colorlesspack.IsGhostModifier;
import thePackmaster.powers.colorlesspack.GhostPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.transmutationpack.TransmuteCardEffect;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Ghost extends AbstractColorlessPackCard implements StartupCard {
    public final static String ID = makeID("Ghost");
    // intellij stuff skill, enemy, uncommon, , , , , 12, 4

    public Ghost() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction(scream()));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new FireballEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.5F));
        applyToEnemy(m, new GhostPower(m, magicNumber));
    }

    private static String scream() {
        int roll = MathUtils.random(1);
        if (roll == 0) {
            return "VO_NEMESIS_2A";
        } else {
            return "VO_NEMESIS_2B";
        }
    }

    private static boolean canDisguiseAs(AbstractCard target) {
        return target.cost != -2 && !target.cardID.equals(Ghost.ID) && !target.cardID.equals(ThePrism.ID) && !CardModifierManager.hasModifier(target, IsGhostModifier.ID);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                List<AbstractCard> possibilities = AbstractDungeon.player.drawPile.group.stream().filter(Ghost::canDisguiseAs).collect(Collectors.toList());
                if (!possibilities.isEmpty() && AbstractDungeon.player.drawPile.contains(Ghost.this)) {
                    int index = AbstractDungeon.player.drawPile.group.indexOf(Ghost.this);
                    AbstractDungeon.player.drawPile.removeCard(Ghost.this);
                    AbstractCard disguise = Wiz.getRandomItem(possibilities, AbstractDungeon.cardRandomRng).makeStatEquivalentCopy();
                    CardModifierManager.addModifier(disguise, new IsGhostModifier(Ghost.this));
                    if (index > 0) {
                        AbstractDungeon.player.drawPile.group.add(index, disguise);
                    } else {
                        AbstractDungeon.player.drawPile.addToRandomSpot(disguise);
                    }
                    HashMap<AbstractCard, AbstractCard> toFrom = new HashMap<>();
                    toFrom.put(Ghost.this.makeStatEquivalentCopy(), disguise.makeStatEquivalentCopy());
                    AbstractDungeon.effectsQueue.add(new TransmuteCardEffect(toFrom, 1F));
                }
            }
        });
        return false;
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}