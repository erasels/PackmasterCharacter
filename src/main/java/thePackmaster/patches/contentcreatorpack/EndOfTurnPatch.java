package thePackmaster.patches.contentcreatorpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import thePackmaster.cards.contentcreatorpack.MegaEtherealCard;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Arrays;

public class EndOfTurnPatch {
    @SpirePatch(clz = AbstractRoom.class, method = "endTurn")
    public static class EndTurn {
        @SpirePrefixPatch
        public static void Prefix(AbstractRoom __instance) {
            if (Wiz.isInCombat()) {
                AbstractPlayer p = AbstractDungeon.player;
                ArrayList<AbstractCard> handToExhaust = new ArrayList<>();
                ArrayList<AbstractCard> drawToExhaust = new ArrayList<>();
                ArrayList<AbstractCard> discardToExhaust = new ArrayList<>();
                for (CardGroup cardGroup : Arrays.asList(new CardGroup[]{p.hand, p.drawPile, p.discardPile})) {
                    for (AbstractCard q : cardGroup.group) {
                        if (q instanceof MegaEtherealCard) {
                            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(q, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                            if (cardGroup == p.hand) {
                                handToExhaust.add(q);
                            } else if (cardGroup == p.drawPile) {
                                drawToExhaust.add(q);
                            } else {
                                discardToExhaust.add(q);
                            }
                        }
                    }
                }

                for (AbstractCard q : handToExhaust) {
                    p.hand.moveToExhaustPile(q);
                }
                for (AbstractCard q : drawToExhaust) {
                    p.drawPile.moveToExhaustPile(q);
                }
                for (AbstractCard q : discardToExhaust) {
                    p.discardPile.moveToExhaustPile(q);
                }
            }
        }
    }
}
