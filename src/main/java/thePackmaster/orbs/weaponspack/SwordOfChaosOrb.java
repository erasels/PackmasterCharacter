package thePackmaster.orbs.weaponspack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import thePackmaster.effects.weaponspack.DamageCurvyEffect;
import thePackmaster.effects.weaponspack.DamageLineEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SwordOfChaosOrb extends AbstractWeaponOrb {

    public static final String ID = makeID("SwordOfChaosOrb");
    private static final OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString(ID);
    public static final String NAME = orbStrings.NAME;
    public static final String[] DESCRIPTION = orbStrings.DESCRIPTION;

    public SwordOfChaosOrb(int attack, int durability, boolean justAddedUsingAttackCard) {
        super(ID, NAME, DESCRIPTION[0], getOrbImagePath(ID), attack, durability, justAddedUsingAttackCard);
        this.attack = attack;
        this.durability = durability;
    }

    @Override
    public void effectOnUse() {
        for (int i = 0; i < 36; i++) {
            AbstractDungeon.effectList.add(new DamageLineEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(0.905F, 0F, 0.905F, 1), ((10 * i) + MathUtils.random(-10, 10) + offset)));
            if (i % 2 == 0) {
                AbstractDungeon.effectList.add(new DamageCurvyEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(0.905F, 0F, 0.905F, 1)));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy(), false));
    }
}
