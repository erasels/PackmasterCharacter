package thePackmaster.cards.monsterhunterpack;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import thePackmaster.actions.monsterhunterpack.CarveAction;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.HashMap;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CarvingKnife extends AbstractMonsterHunterCard {
    public final static String ID = makeID("CarvingKnife");

    public static final int DAMAGE = 6;
    public static final int UPG_DAMAGE = 3;
    private final ArrayList<AbstractCard> cardToPreview = new ArrayList<>();
    private float rotationTimer;
    private int previewIndex = 0;

    protected float getRotationTimeNeeded() {
        return 1.0F;
    }

    @Override
    public void update() {
        super.update();
        if (!cardToPreview.isEmpty() && AbstractDungeon.actionManager.isEmpty()) {
            if (hb.hovered) {
                if (rotationTimer <= 0F) {
                    rotationTimer = getRotationTimeNeeded();
                    if (previewIndex == cardToPreview.size() - 1) {
                        previewIndex = 0;
                    } else {
                        previewIndex++;
                    }
                    if (previewIndex >= cardToPreview.size()) {
                        previewIndex = cardToPreview.size() - 1;
                    }
                    cardsToPreview = cardToPreview.get(previewIndex);
                } else {
                    rotationTimer -= Gdx.graphics.getRawDeltaTime();
                }
            }
        }
    }


    public CarvingKnife() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CarveAction(m, new DamageInfo(m, damage, DamageInfo.DamageType.NORMAL), this));
        cardToPreview.clear();
        previewIndex = 0;
    }

    public void applyPowers() {
        super.applyPowers();
        this.cardToPreview.clear();
        for (AbstractMonster m : Wiz.getEnemies()) {
            if (!m.isDeadOrEscaped()) {
                if ((m.type == AbstractMonster.EnemyType.ELITE || m.type == AbstractMonster.EnemyType.BOSS) || isSlimebossOffspring(m.id)) {
                    if (!cardToPreview.contains(getMonsterWeapon(m.id))) {
                        cardToPreview.add(getMonsterWeapon(m.id));
                    }
                }
            }
        }
    }

    private static final HashMap<String, String> monsterWeapons;
    static {
        monsterWeapons = new HashMap<>();
        monsterWeapons.put(GremlinNob.ID, SkullClub.ID);
        monsterWeapons.put(Sentry.ID, CoreBlaster.ID);
        monsterWeapons.put(Lagavulin.ID, ShellPauldrons.ID);
        monsterWeapons.put(TheGuardian.ID, GuardianShield.ID);
        monsterWeapons.put(Hexaghost.ID, InfernoDaggers.ID);
        monsterWeapons.put(SlimeBoss.ID, SlimeHammer.ID);
        // Has specific Slimeboss room condition
        monsterWeapons.put(SpikeSlime_L.ID, SlimeHammer.ID);
        monsterWeapons.put(AcidSlime_L.ID, SlimeHammer.ID);
        monsterWeapons.put(SpikeSlime_M.ID, SlimeHammer.ID);
        monsterWeapons.put(AcidSlime_M.ID, SlimeHammer.ID);

        monsterWeapons.put(BookOfStabbing.ID, StabManual.ID);
        monsterWeapons.put(Taskmaster.ID, SlaverWhip.ID);
        monsterWeapons.put(GremlinLeader.ID, GremlinLance.ID);
        monsterWeapons.put(Champ.ID, ChampMail.ID);
        monsterWeapons.put(TheCollector.ID, CursedBow.ID);
        monsterWeapons.put(BronzeAutomaton.ID, HyperBlaster.ID);
        monsterWeapons.put(Nemesis.ID, EphemeralShroud.ID);
        monsterWeapons.put(GiantHead.ID, StoneHelm.ID);
        monsterWeapons.put(TimeEater.ID, TimepieceTiara.ID);
        monsterWeapons.put(AwakenedOne.ID, AwakenedRitualDagger.ID);
        monsterWeapons.put(Donu.ID, DonuAmulet.ID);
        monsterWeapons.put(Deca.ID, DecaAmulet.ID);
        monsterWeapons.put(SpireShield.ID, SpireShield.ID);
        monsterWeapons.put(SpireSpear.ID, SpireSpear.ID);
        monsterWeapons.put(Reptomancer.ID, SerpentineDagger.ID);
        monsterWeapons.put(CorruptHeart.ID, CorruptedBlade.ID);
    }

    public static AbstractCard getMonsterWeapon(String id) {
        return CardLibrary.getCard(monsterWeapons.getOrDefault(id, Hyperbeam.ID));
    }

    public static boolean isSlimebossOffspring(String monsterID) {
        return AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss
                && ((monsterID.equals(AcidSlime_L.ID) || monsterID.equals(SpikeSlime_L.ID) || monsterID.equals(AcidSlime_M.ID) || monsterID.equals(SpikeSlime_M.ID)));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}