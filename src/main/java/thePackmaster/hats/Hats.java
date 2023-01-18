package thePackmaster.hats;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.*;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.hats.specialhats.SpecialHat;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.ImageHelper;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import static thePackmaster.hats.HatMenu.specialHats;

public class Hats {
    public static String currentHat;

    public static void removeHat(boolean inRun) {
        if (!inRun && HatMenu.dummy == null) {
            return;
        }

        if (headbone == null && !inRun) {
            setupSkeleton(HatMenu.dummy, true);
        } else if (playerbone == null && inRun) {
            setupSkeleton(AbstractDungeon.player, false);
        }
        if (inRun) {
            skeleton = ReflectionHacks.getPrivate(Wiz.p(), AbstractCreature.class, "skeleton");
        } else {
            skeleton = ReflectionHacks.getPrivate(HatMenu.dummy, AbstractCreature.class, "skeleton");
        }

        if (skeleton == null) {
            SpireAnniversary5Mod.logger.info("Hats error! Skeleton is null when attempting removeHat!");
            return;
        }

        skeleton.findBone("HatBone").setScale(1F);
        skeleton.findBone("HairBone").setScale(1F);
        String imgPath = getImagePathFromHatID("No");
        if (inRun) {
            skeleton.getAttachment(attachmentSlotIndex, "hat");
            if (attachment != null) {
                TextureRegion region = ImageHelper.asAtlasRegion(TexLoader.getTexture(imgPath));
                attachment.setRegion(region);
            }
        } else {
            skeleton.getAttachment(attachmentSlotIndex, "hat");
            if (attachmentDummy != null) {
                TextureRegion region = ImageHelper.asAtlasRegion(TexLoader.getTexture(imgPath));
                attachmentDummy.setRegion(region);
            }
        }

    }

    private static Skeleton skeleton;

    private static Bone headbone;
    private static Bone playerbone;
    private static Slot headslot;
    private static Slot playerHeadSlot;
    private static int foundHeadSlot;
    private static int playerFoundHeadSlot;
    private static int attachmentSlotIndex;

    private static RegionAttachment attachment;
    private static RegionAttachment attachmentDummy;

    private static void setupSkeleton(AbstractPlayer p, boolean isDummy) {
        int headslotIndex = 0;
        skeleton = ReflectionHacks.getPrivate(p, AbstractCreature.class, "skeleton");
        if (skeleton == null) {
            SpireAnniversary5Mod.logger.info("Hats error! Skeleton is null when attempting setup!");
            return;
        }

        String bonename;
        String slotname;


        Array<Bone> possiblebones = skeleton.getBones();
        for (Bone b : possiblebones) {
            bonename = b.toString().toLowerCase();
            if (bonename.equals("head")) {
                if (isDummy)
                    headbone = b;
                else {
                    playerbone = b;
                }
                break;
            }

        }

        Array<Slot> possibleslots = skeleton.getSlots();
        for (Slot s : possibleslots) {
            slotname = s.getBone().toString().toLowerCase();
            if (slotname.equals("head")) {
                if (isDummy) {
                    headslot = s;
                } else {
                    playerHeadSlot = s;
                }
                break;
            }
            headslotIndex++;
        }

        if (isDummy)
            foundHeadSlot = headslotIndex;
        else
            playerFoundHeadSlot = headslotIndex;
    }

    public static void addHat(boolean inRun, String hatID) {
        if (headslot == null && !inRun) {
            setupSkeleton(HatMenu.dummy, true);
        } else if (playerHeadSlot == null && inRun) {
            setupSkeleton(AbstractDungeon.player, false);
        }
        if (inRun) {
            skeleton = ReflectionHacks.getPrivate(Wiz.p(), AbstractCreature.class, "skeleton");
        } else {
            skeleton = ReflectionHacks.getPrivate(HatMenu.dummy, AbstractCreature.class, "skeleton");
        }

        String imgPath = getImagePathFromHatID(hatID);
        if (!TexLoader.testTexture(imgPath)) {
            removeHat(inRun);
            return;
        }

        if (skeleton.getAttachment(inRun ? playerFoundHeadSlot : foundHeadSlot, "hat") == null) {
            SpireAnniversary5Mod.logger.info("starting attachment process, in run = " + inRun);
            String attachName = inRun ? playerbone.toString() : headbone.toString();
            int slotIndex = inRun ? playerFoundHeadSlot : foundHeadSlot;

            SpireAnniversary5Mod.logger.info("creating slot on " + slotIndex);
            // Create a new slot for the attachment
            Slot origSlot = inRun ? playerHeadSlot : headslot;
            Slot slotClone = new Slot(new SlotData(origSlot.getData().getIndex(), attachName, origSlot.getBone().getData()), origSlot.getBone());
            slotClone.getData().setBlendMode(origSlot.getData().getBlendMode());
            skeleton.getSlots().insert(slotIndex, slotClone);

            Array<Slot> drawOrder = skeleton.getDrawOrder();
            drawOrder.add(slotClone);
            skeleton.setDrawOrder(drawOrder);

            TextureRegion region = ImageHelper.asAtlasRegion(TexLoader.getTexture(imgPath));
            Texture tex = TexLoader.getTexture(imgPath);
            SpireAnniversary5Mod.logger.info("texture loading as " + imgPath);
            tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

            if (inRun) {
                SpireAnniversary5Mod.logger.info("creating attachment in run");
                attachment = new RegionAttachment("hat");
                attachment.setRegion(region);
                attachment.setWidth(tex.getWidth());
                attachment.setHeight(tex.getHeight());
                attachment.setX(1F);
                attachment.setY(31F * Settings.scale);
                attachment.setScaleX(Settings.scale);
                attachment.setScaleY(Settings.scale);
                attachment.updateOffset();

                Skin skin = skeleton.getData().getDefaultSkin();
                skin.addAttachment(slotIndex, attachment.getName(), attachment);
                attachmentSlotIndex = slotIndex;
                SpireAnniversary5Mod.logger.info("attachment slot completed at index " + attachmentSlotIndex);

                skeleton.setAttachment(attachName, attachment.getName());
            } else {
                SpireAnniversary5Mod.logger.info("creating attachment in hat menu");
                attachmentDummy = new RegionAttachment("hat");
                attachmentDummy.setRegion(region);
                attachmentDummy.setWidth(tex.getWidth());
                attachmentDummy.setHeight(tex.getHeight());
                attachmentDummy.setX(1F);
                attachmentDummy.setY(38F * Settings.scale);
                attachmentDummy.setScaleX(Settings.scale);
                attachmentDummy.setScaleY(Settings.scale);
                attachmentDummy.updateOffset();

                Skin skin = skeleton.getData().getDefaultSkin();
                skin.addAttachment(slotIndex, attachmentDummy.getName(), attachmentDummy);
                attachmentSlotIndex = slotIndex;
                SpireAnniversary5Mod.logger.info("attachment slot completed at index " + attachmentSlotIndex);

                skeleton.setAttachment(attachName, attachmentDummy.getName());
            }


            skeleton.findBone("HatBone").setScale(0F);
            AbstractCardPack p = SpireAnniversary5Mod.packsByID.get(hatID);
            if (p != null && p.hatHidesHair) {
                skeleton.findBone("HairBone").setScale(0F);
            }

        } else {
            TextureRegion region = ImageHelper.asAtlasRegion(TexLoader.getTexture(imgPath));
            if (inRun) {
                attachment.setRegion(region);
            } else {
                attachmentDummy.setRegion(region);

            }
            skeleton.findBone("HatBone").setScale(0F);
            AbstractCardPack p = SpireAnniversary5Mod.packsByID.get(hatID);
            if (p != null && p.hatHidesHair) {
                skeleton.findBone("HairBone").setScale(0F);
            } else {
                skeleton.findBone("HairBone").setScale(1F);
            }
        }
    }


    public static String getImagePathFromHatID(String hatID) {
        SpireAnniversary5Mod.logger.info(SpireAnniversary5Mod.modID + "Resources/images/hats/" + hatID.replace(SpireAnniversary5Mod.modID + ":", "") + "Hat.png");
        return SpireAnniversary5Mod.modID + "Resources/images/hats/" + hatID.replace(SpireAnniversary5Mod.modID + ":", "") + "Hat.png";

    }

    public static void atRunStart() {
        playerHeadSlot = null;
        if (currentHat != null) {
            SpireAnniversary5Mod.logger.info("adding run start hat");
            if (HatMenu.invalidHatSelected) {
                SpireAnniversary5Mod.logger.info("an invalid hat was used. returning to default.");
                currentHat = null;
                removeHat(true);
                return;
            }
            addHat(true, currentHat);
        } else {
            removeHat(true);
        }
    }

    private static final float Y_OFF = 13f;
    public static void preRenderPlayer(SpriteBatch sb, AbstractPlayer p) {
        SpecialHat shat = specialHats.get(currentHat);
        if (shat != null && skeleton != null && headbone != null) {
            float x = skeleton.getX() + headbone.getWorldX(), y = skeleton.getY() + headbone.getWorldY() + Y_OFF * headbone.getScaleY();
            shat.preRenderPlayer(sb, p, x, y);
        }
    }
    public static void postRenderPlayer(SpriteBatch sb, AbstractPlayer p) {
        SpecialHat shat = specialHats.get(currentHat);
        if (shat != null && skeleton != null && headbone != null) {
            float x = skeleton.getX() + headbone.getWorldX(), y = skeleton.getY() + headbone.getWorldY() + Y_OFF * headbone.getScaleY();
            shat.postRenderPlayer(sb, p, x, y);
        }
    }
}