package thePackmaster.cards.secretlevelpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.secretlevelpack.EnoughTalkPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class EnoughTalk extends AbstractPackmasterCard {
    public final static String ID = makeID("EnoughTalk");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public EnoughTalk() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_CHAMP_CHARGE"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
        atb(new RemoveSpecificPowerAction(p, p, WeakPower.POWER_ID));
        atb(new RemoveSpecificPowerAction(p, p, FrailPower.POWER_ID));
        applyToSelf(new StrengthPower(p, magicNumber));
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = EnoughTalkPatch.spokeLastTurn ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (EnoughTalkPatch.spokeLastTurn) {
            setCostForTurn(0);
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}