package thePackmaster.cards.downfallpack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.vfx.MegaSpeechBubble;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.downfallpack.NextTurnGainSlimeCrushPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class PrepareCrush extends AbstractPackmasterCard {
    public final static String ID = makeID("PrepareCrush");


    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 3;
    private static final int MAGIC = 1;

    public PrepareCrush() {
        super(ID, 1, AbstractCard.CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);

        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new SlimeCrush();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectList.add(new MegaSpeechBubble(p.hb.cX, p.hb.cY, 1.0F, SlimeBoss.DIALOG[0], true));
        atb(new ShakeScreenAction(0.3F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.LOW));

        atb(new GainBlockAction(p, p, this.block));
        atb(new ApplyPowerAction(p, p, new EnergizedPower(p, magicNumber), magicNumber));
        atb(new ApplyPowerAction(p, p, new NextTurnGainSlimeCrushPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }

}
