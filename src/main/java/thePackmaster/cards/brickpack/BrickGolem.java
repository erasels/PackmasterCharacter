package thePackmaster.cards.brickpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.applyToSelf;

public class BrickGolem extends AbstractBrickCard implements StartupCard, OnObtainCard {
    public final static String ID = makeID(BrickGolem.class.getSimpleName());
    private static final int COST = -2;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 6;
    private static final int UPGRADE_MAGIC = 2;
    private static final int SECOND_MAGIC = 3;
    private static final int UPGRADE_SECOND = 2;

    public BrickGolem() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        cardsToPreview = new HeavyBrick();
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public boolean atBattleStartPreDraw() {
        applyToSelf(new MetallicizePower(adp(), magicNumber));
        applyToSelf(new ArtifactPower(adp(), secondMagic));
        return true;
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new HeavyBrick(),
                Settings.WIDTH / 3, Settings.HEIGHT / 2, false));
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new HeavyBrick(),
                Settings.WIDTH * 2 / 3, Settings.HEIGHT / 2, false));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upgradeSecondMagic(UPGRADE_SECOND);
    }
}
