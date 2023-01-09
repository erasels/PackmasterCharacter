package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import java.util.HashSet;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RippedPecs extends AbstractPackmasterCard {
    public final static String ID = makeID("RippedPecs");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public RippedPecs() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int strength = getPacksPlayedThisCombat(false);
                Wiz.applyToSelfTop(new StrengthPower(p, strength));
                this.isDone = true;
            }
        });
    }

    public void upp() {
        this.exhaust = false;
    }

    @Override
    public void applyPowers() {
        this.rawDescription = (this.upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) + cardStrings.EXTENDED_DESCRIPTION[0].replace("{0}", this.getPacksPlayedThisCombat(true) + "");
        this.initializeDescription();
    }

    private int getPacksPlayedThisCombat(boolean forApplyPowers) {
        int n = forApplyPowers ? 1 : 2;
        HashSet<String> packs = new HashSet<>();
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= n) {
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat.subList(0, AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - n + 1)) {
                String parentID = SpireAnniversary5Mod.cardParentMap.get(c.cardID);
                if (parentID != null) {
                    packs.add(parentID);
                }
            }
        }
        return packs.size();
    }
}