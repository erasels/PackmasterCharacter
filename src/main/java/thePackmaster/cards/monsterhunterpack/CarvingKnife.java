package thePackmaster.cards.monsterhunterpack;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import thePackmaster.actions.monsterhunterpack.CarveAction;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

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
                    if (previewIndex >= cardToPreview.size()){
                        previewIndex = cardToPreview.size()-1;
                    }
                    cardsToPreview = cardToPreview.get(previewIndex);
                } else {
                    rotationTimer -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }


    public CarvingKnife() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        this.exhaust = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CarveAction(m, new DamageInfo(m, damage, DamageInfo.DamageType.NORMAL), this));
        this.cardToPreview.clear();
        this.previewIndex = 0;
    }

    public void applyPowers(){
        super.applyPowers();
        this.cardToPreview.clear();
        for (AbstractMonster m : Wiz.getEnemies()){
            if (!m.isDeadOrEscaped() && (m.type == AbstractMonster.EnemyType.ELITE || m.type == AbstractMonster.EnemyType.BOSS)){
                if (!this.cardToPreview.contains(MonsterWeapon(m.id))) {
                    this.cardToPreview.add(MonsterWeapon(m.id));
                }
            }
            if (!m.isDeadOrEscaped() && AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
                if (m.id.equals("AcidSlime_L") || m.id.equals("SpikeSlime_L")) {
                    if (!this.cardToPreview.contains(MonsterWeapon(m.id))) {
                        this.cardToPreview.add(MonsterWeapon(m.id));
                    }
                }
            }
        }
    }

    public AbstractCard MonsterWeapon(String id){
        if (id == null){
            return new Hyperbeam();
        }
        switch (id){
            case "GremlinNob":
                return new SkullClub();
            case "Sentry":
                return new CoreBlaster();
            case "Lagavulin":
                return new ShellPauldrons();
            case "TheGuardian":
                return new GuardianShield();
            case "Hexaghost":
                return new InfernoDaggers();
            case "SlimeBoss":
                return new SlimeHammer();
            case "SpikeSlime_L":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss){
                    return new SlimeHammer();
                }
            case "AcidSlime_L":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss){
                    return new SlimeHammer();
                }
            case "SpikeSlime_M":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss){
                    return new SlimeHammer();
                }
            case "AcidSlime_M":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss){
                    return new SlimeHammer();
                }
            case "BookOfStabbing":
                return new StabManual();
            case "SlaverBoss":
                return new SlaverWhip();
            case "GremlinLeader":
                return new GremlinLance();
            case "Champ":
                return new ChampMail();
            case "TheCollector":
                return new CursedBow();
            case "BronzeAutomaton":
                return new HyperBlaster();
            case "Nemesis":
                return new EphemeralShroud();
            case "GiantHead":
                return new StoneHelm();
            case "TimeEater":
                return new TimepieceTiara();
            case "AwakenedOne":
                return new AwakenedRitualDagger();
            case "Donu":
                return new DonuAmulet();
            case "Deca":
                return new DecaAmulet();
            case "SpireShield":
                return new SpireShield();
            case "SpireSpear":
                return new SpireSpear();
            case "Reptomancer":
                return new SerpentineDagger();
            case "CorruptHeart":
                return new CorruptedBlade();
            default:
                return new Hyperbeam();
        }
    }

    public void SetFleeting(boolean set){
        FleetingField.fleeting.set(this, set);
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}