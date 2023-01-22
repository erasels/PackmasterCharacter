package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.legacypack.ShootAnythingAction;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StickStrike extends AbstractLegacyCard {
    public final static String ID = makeID("StickStrike");

    private static final int POWER = 8;
    private static final int UPGRADE_BONUS = 2;


    public static StickStrike lastPlayed = null;

    public StickStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = POWER;
        tags.add(CardTags.STRIKE);
        cardsToPreview = new Stick();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!purgeOnUse) lastPlayed = this;

        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        AbstractCard stick = new Stick();
        if (this.upgraded) stick.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(stick,1,true,false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_BONUS);
        cardsToPreview.upgrade();
    }
}