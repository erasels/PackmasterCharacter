package thePackmaster.cards.aggressionpack;

import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class Slam extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Vindicate");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_DAMAGE = 2;

    public Slam() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new WallopAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }
}
