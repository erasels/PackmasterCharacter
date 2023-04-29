package thePackmaster.cards.instadeathpack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.instadeathpack.DamageMultiplierModifier;
import thePackmaster.vfx.instadeathpack.RandomReversingSlashEffect;


import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SlashDraw extends AbstractInstadeathCard {
    public final static String ID = makeID("SlashDraw");

    public SlashDraw() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 1;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    private List<RandomReversingSlashEffect> activeVfx = new ArrayList<>();

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            float lerp = Math.min(1, activeVfx.size() / 9f);
            Color c1 = Color.GOLD.cpy();
            c1.lerp(Color.RED, lerp);
            Color c2 = Color.BLACK.cpy();
            c2.lerp(Color.PURPLE, lerp);

            RandomReversingSlashEffect slashEffect = new RandomReversingSlashEffect(m, c1, c2);
            addToBot(new VFXAction(slashEffect));
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    for (RandomReversingSlashEffect oldSlash : activeVfx) {
                        if (oldSlash != slashEffect)
                            oldSlash.reverse(c1, c2);
                    }
                }
            });

            activeVfx.add(slashEffect);

            dmg(m, AbstractGameAction.AttackEffect.NONE);
            addToBot(new DrawCardAction(1, new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (DrawCardAction.drawnCards.isEmpty()) {
                        for (RandomReversingSlashEffect e : activeVfx) {
                            e.end();
                        }
                        return;
                    }

                    AbstractCard drawn = DrawCardAction.drawnCards.get(0);
                    if (drawn == null || drawn.type != CardType.ATTACK) {
                        for (RandomReversingSlashEffect e : activeVfx) {
                            e.end();
                        }
                        return;
                    }

                    queueMultipliedCard(SlashDraw.this, m);
                }
            }));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }



    private static void queueMultipliedCard(SlashDraw c, AbstractMonster m) {
        AbstractCard tmp = c.makeSameInstanceOf();
        if (!(tmp instanceof SlashDraw))
            return; //???????

        SlashDraw slash = (SlashDraw) tmp;
        CardModifierManager.addModifier(slash, new DamageMultiplierModifier(c.magicNumber));
        ((SlashDraw) tmp).activeVfx = c.activeVfx;

        AbstractDungeon.player.limbo.addToBottom(slash);
        slash.current_x = c.current_x;
        slash.current_y = c.current_y;
        int extraCount = 0;

        for (CardQueueItem queueItem : AbstractDungeon.actionManager.cardQueue) {
            if (queueItem.card.uuid.equals(c.uuid)) {
                ++extraCount;
            }
        }

        slash.target_y = Settings.HEIGHT / 2.0F;
        switch (extraCount) {
            case 0:
                slash.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.xScale;
                break;
            case 1:
                slash.target_x = Settings.WIDTH / 2.0F + 300.0F * Settings.xScale;
                break;
            case 2:
                slash.target_x = Settings.WIDTH / 2.0F - 600.0F * Settings.xScale;
                break;
            case 3:
                slash.target_x = Settings.WIDTH / 2.0F + 600.0F * Settings.xScale;
                break;
            default:
                slash.target_x = MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F);
                slash.target_y = MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F);
        }

        if (m != null) {
            slash.calculateCardDamage(m);
        }

        slash.purgeOnUse = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(slash, m, c.energyOnUse, true, true), true);
    }
}