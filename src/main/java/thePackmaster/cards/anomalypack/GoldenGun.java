package thePackmaster.cards.anomalypack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.hermitpack.EnumPatch;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.util.Wiz;

public class GoldenGun extends AbstractPackmasterCard implements StartupCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GoldenGun");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int ATTACK_DMG = 50;
    private static final int UPGRADE_PLUS_DMG = 14;
    private static final int COST = 1;
    public boolean loaded;


    public GoldenGun() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        this.damage = this.baseDamage = ATTACK_DMG;
        loaded=false;
    }

    public void load() {
        this.loaded=true;
    }

    public boolean loaded(){
        return this.loaded;
    }

    @Override
    public void upp() {
        //this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        loaded=false;
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), EnumPatch.HERMIT_GUN));

    }


    public void triggerOnGlowCheck() {
        this.glowColor = AbstractPackmasterCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (this.loaded)  {
            this.glowColor = AbstractPackmasterCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }

    }


    public boolean atBattleStartPreDraw() {
        Wiz.shuffleIn(new GoldenRound());
        return false;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return this.loaded;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            //rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
