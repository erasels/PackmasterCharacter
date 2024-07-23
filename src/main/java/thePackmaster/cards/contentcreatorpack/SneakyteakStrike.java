package thePackmaster.cards.contentcreatorpack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.topDeck;

public class SneakyteakStrike extends AbstractContentCard {
    public final static String ID = makeID("SneakyteakStrike");

    public SneakyteakStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        tags.add(CardTags.STRIKE);
        cardsToPreview = new DarksideSlap();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        AbstractCard q = new DarksideSlap();
        if (upgraded) q.upgrade();
        topDeck(q);
    }

    public void upp() {
        upgradeDamage(2);
        cardsToPreview.upgrade();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tooltips = new ArrayList<>();
        tooltips.add(new TooltipInfo(BaseMod.getKeywordProper("anniv5:megathereal"), BaseMod.getKeywordDescription("anniv5:megathereal")));
        return tooltips;
    }
}