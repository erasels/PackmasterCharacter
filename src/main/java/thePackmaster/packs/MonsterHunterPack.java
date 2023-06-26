package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.monsterhunterpack.*;

import java.util.ArrayList;

public class MonsterHunterPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("MonsterHunterPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public MonsterHunterPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 2, 2, 3, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(CarvingKnife.ID);
        cards.add(HuntingStrike.ID);
        cards.add(Greatsword.ID);
        cards.add(HuntingContract.ID);
        cards.add(HuntingInstincts.ID);
        cards.add(Focus.ID);
        cards.add(TranqBomb.ID);
        cards.add(Paintball.ID);
        cards.add(PerfectDodge.ID);
        cards.add(HunterRank.ID);
        cards.add(AwakenedRitualDagger.ID);
        cards.add(ChampMail.ID);
        cards.add(CoreBlaster.ID);
        cards.add(CoreBlinder.ID);
        cards.add(CursedBow.ID);
        cards.add(DecaAmulet.ID);
        cards.add(DonuAmulet.ID);
        cards.add(EphemeralShroud.ID);
        cards.add(GremlinLance.ID);
        cards.add(GuardianShield.ID);
        cards.add(HyperBlaster.ID);
        cards.add(InfernoDaggers.ID);
        cards.add(ShellPauldrons.ID);
        cards.add(SkullClub.ID);
        cards.add(SlaverWhip.ID);
        cards.add(SlimeHammer.ID);
        cards.add(SpireShield.ID);
        cards.add(SpireSpear.ID);
        cards.add(StabManual.ID);
        cards.add(StoneHelm.ID);
        cards.add(TimepieceTiara.ID);
        cards.add(SerpentineDagger.ID);

        return cards;
    }
}

